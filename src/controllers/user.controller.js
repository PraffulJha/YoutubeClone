import { ApiError } from '../utils/apiError.js'
import  { asyncHandler } from '../utils/asyncHandler.js'
import { User } from '../models/user.model.js'
import { uploadOnCloudinary } from '../services/cloudinary.service.js'
import { ApiResponse } from '../utils/ApiResponse.js'
import jwt from "jsonwebtoken"
import mongoose from 'mongoose'

const generateAcessAndRefreshTokens = async(userId) =>  {
    try{
        const user = await User.findById(userId)
       const accessToken =  user.generateAccessToken()
       const refershToken =  user.generateRefreshToken()
       user.refershToken = refershToken
       await user.save({ validateBeforeSave: false })

       return {accessToken,refershToken}
    }
    catch(err){
        throw new ApiError("Something went wrong while genearting access token" , err)
    }
}

const registerUser  = asyncHandler( async (req,res) => {
   // get user details from frontend
    // validation - not empty
    // check if user already exists: username, email
    // check for images, check for avatar
    // upload them to cloudinary, avatar
    // create user object - create entry in db
    // remove password and refresh token field from response
    // check for user creation
    // return res
    const {username,fullname,email,password} = req.body
    if(
        [fullname,email,password,username].some((field) => field?.trim() === "") 
    ){
        throw new ApiError(400,"All fields are required")
    }
    const existedUser = await User.findOne({
        $or:[{email}, {username}]
    })
    if(existedUser) {
        throw new ApiError(401,"User with email or username already exists")
    }
    const avatarLocpath = req.files?.avatar[0]?.path;
    // const coverImageLocalPath = req.files?.coverimg[0]?.path;
    let coverImageLocalPath;
    if (req.files && Array.isArray(req.files.coverImage) && req.files.coverImage.length > 0) {
        coverImageLocalPath = req.files.coverImage[0].path
    }
    if(!avatarLocpath){
        throw new ApiError(400,"Avatar image is required")
    }
    const Avatar = await uploadOnCloudinary(avatarLocpath)
    const coverimage = await uploadOnCloudinary(coverImageLocalPath)
    if(!Avatar){
        throw new ApiError(400,"Avatar image is required")
    }

    const user = await User.create({
        fullname,
        avatar : Avatar.url,
        coverimg : coverimage?.url || "",
        email,
        password,
        username: username.toLowerCase()

    })
    const createdUser = await User.findById(user._id).select("-password -refershToken")
    if(!createdUser){
        throw new ApiError(400,"Something went wrong while creating user")
    }
    return res.status(201).json( new ApiResponse(200),createdUser,"user registed sucessfully")

})
const loginUser = asyncHandler(async (req,res) => {
    // req body -> data
    // username or email
    // find the user
    // password check 
    // access and refresh token
    // send secure cookies
    // response
    const { email,username,password } = req.body
    console.log(email);
    if(!username && !email){
        throw new ApiError(400,res.status(201).cookie("accesToken",accessToken,options).cookie("username or email required!"))
    }
     const user = await User.findOne({
        $or: [{username},{email}]
    })

    if(!user){
        throw new ApiError(404,"User does not exits !")
    }
    const isPasswordValid = await user.isPasswordCorrect(password)
    if(!isPasswordValid){
        throw new ApiError(401,"Invalid user creaditials !")
    }
    const {accessToken,refershToken} = await
     generateAcessAndRefreshTokens(user._id)

     const loggedInUser = await User.findById(user._id).select("-password -refreshToken")

     const options = {
        httpOnly : true,
        secure: true
     }
     return res.status(201)
     .cookie("accessToken",accessToken,options)
     .cookie("refreshToken",refershToken,options)
     .json(
        new ApiResponse(200,
           {
            user: loggedInUser, accessToken,
            refershToken
           },
           "User loggedIn sucessFully"
        )
     )
})
const logOutUser = asyncHandler(async (req,res) => {
    await User.findByIdAndUpdate(req.user._id,
        {
            $set : {
                refershToken : undefined
            }
           
    },
    {
        new : true
    }
)
const options = {
    httpOnly : true,
    secure: true
 }
 return res.status(201).
        clearCookie("accessToken",accessToken,options)
        .clearCookie("refreshToken",refershToken,options)
        .json(new ApiResponse(200,{},"User logged Out SuccessFully!"))
})

const refreshAccessToken = asyncHandler (async (req,res) => {
    const incomingRefreshToken = req.cookies.refershToken || req.body.refershToken
    if(!incomingRefreshToken){
        throw new ApiError(401,"unauthorized request")
    }
    try {
        const decodedToken = jwt.verify(
            incomingRefreshToken,
            process.env.REFRESH_TOKEN_SECRET
        )
        console.log(decodedToken);
        const user = await User.findById(decodedToken?._id)
        if(!user){
            throw new ApiError(401,"Invalid refresh token !")
        }
        if(incomingRefreshToken !== user?.refershToken){
            throw new ApiError(401, "RefershToken is already expired or used")
        }
        const options = {
            httpOnly : true,
            secure: true
         }
         const {accessToken,newRefershToken} = await generateAcessAndRefreshTokens(user._id)
    
         return res.status(201).
         cookie("accessToken",accessToken,options)
         .cookie("refershToken",newRefershToken,options)
         .json(
            new ApiResponse(201,{accessToken,refershToken : newRefershToken},"access Token refreshed!")
         )
    } catch (error) {
        throw new ApiError(401,error.message || "Invalid refreshToken")
    }
})

const changeCurrentPassword = asyncHandler (async (req,res) => {
    const { oldPassword, newPassword,confirmPassword } = req.body
    if(newPassword !== confirmPassword){
        throw new ApiError(400,"password is incorrect")
    }
    const user = await User.findById(req.user?._id)
    const ispasswordCorrect =  await user.isPasswordCorrect(oldPassword)
    if(!ispasswordCorrect){
        throw new ApiError(400,"Invalid old password")
    }
    user.password = newPassword
    await user.save({validateBeforeSave : false})

    return res.status(201)
    .json(new ApiResponse(201,"Password changed sucessfully!"))
})

const getCurrentUser = asyncHandler( async(req,res) => {
    res.status(200).json(200,req.user,"current user fetched sucessfully")
})

const updateUserAccountDetails = asyncHandler(async (req,res) => {
    const {fullname,email} = req.body
    if(!fullname || !email){
        throw new ApiError(401,"ALl field are required")
    }
    const user = await User.findByIdAndUpdate(req.user?._id,
        {
            $set:{
                fullname : fullname,
                email : email

            }
        },
        {new: true}
    ).select("-password")
    return res.status(200).json(new ApiResponse(200,user,"Account details updated sucessfully!!"))
})

const updateUserAvatar = asyncHandler(async(req,res) => {
    const avatarLocalPath = req.file?.path
    if(!avatarLocalPath){
        throw new ApiError(400,"Avatar image is required !")
    }
    const avatar = await uploadOnCloudinary(avatar)
    if(!avatar){
        throw new ApiError(400,"Error while uploading the avatar")
    }
    const user = await User.findByIdAndUpdate(req.user?._id,{
        $set: {
            avatar : avatar.url
        }
    }, {new:true}).select("-password")
    return res.status(201).json(new ApiResponse(201,user,"avatar image updated successfully"))
})

const updateUsercoverImg = asyncHandler(async(req,res) => {
    const coverImgLocalPath = req.file?.path
    if(!coverImgLocalPath){
        throw new ApiError(400,"Cover image is required !")
    }
    const coverImg = await uploadOnCloudinary(avatar)
    if(!coverImg){
        throw new ApiError(400,"Error while uploading the image")
    }
    const user = await User.findByIdAndUpdate(req.user?._id,{
        $set: {
            coverimg : coverImg.url
        }
    }, {new:true}).select("-password")
    return res.status(201).json(new ApiResponse(201,user,"cover image updated successfully"))
})

const getUserChannelProfile = asyncHandler(async(req,res) => {
    const {username} = req.params
    if(!username?.trim()) {
        throw new ApiError(400,"USer Name is missing!")
    } 
    const channel = await User.aggregate([
        {
            $match: {
                username: username?.toLowerCase()
            }
        },
        {
            $lookup: {
                from:"subscriptions",
                localField: "_id",
                foreignField: "channel",
                as : "subscribers"
            }
        },
        {
            $lookup : {
                from:"subscriptions",
                localField: "_id",
                foreignField: "subscriber",
                as : "subscribedTo"
            }
        },
        {
            $addFields: {
                subscribersCount: {
                    $size: "$subscribers"
                },
                channelsSubscribedToCount: {
                    $size: "$subscribedTo"
                },
                isSubscribed:{
                    $cond :{
                        if: {$in: [req.user?._id,"$subscribers.subscriber"]},
                        then: true,
                        else: false
                    }
                }
            }
        },
        {
            $project: {
                fullname: 1,
                username: 1,
                subscribersCount :1,
                isSubscribed: 1,
                channelsSubscribedToCount,
                avatar :1,
                email: 1,
                coverimg: 1

            }
        }
    ])
    if(!channel?.length){
        throw new ApiError(404,"channel does not exist!")
    }

    return res.status(201).json(new ApiResponse(201,channel[0],"User channel fetched sucessfully !"))
})
const getWatchHistory = asyncHandler(async(req,res) => {
   const user = await User.aggregate([
    {
        $match: {
            _id: mongoose.Types.ObjectId(req.user?._id)
        }
    },
    {
        $lookup: {
            from: "videos",
            localField: "watchHistory",
            foreignField:"_id",
            as: "watchHistory",
            pipeline : [
                {
                    $lookup: {
                        from: "users",
                        localField: "owner",
                        foreignField: "_id",
                        as : "owner",
                        pipeline: [
                            {
                                $project: {
                                    fullname: 1,
                                    username: 1,
                                    avatar: 1
                                }
                            },
                            {
                                $addFields: {
                                    owner:{
                                        $first:"owner"
                                    }
                                }
                            }
                        ]
                    }
                }
            ]
        }
    }
   ])
   return res.status(201).json(new ApiResponse(201,user[0].watchHistory,"Watch history fetched successfully!"))
})
export {
    registerUser,
    loginUser,
    logOutUser,
    refreshAccessToken,
    changeCurrentPassword,
    getCurrentUser,
    updateUserAccountDetails,
    updateUserAvatar,
    updateUsercoverImg,
    getUserChannelProfile,
    getWatchHistory
}