import { Router } from "express";
import { loginUser, logOutUser, registerUser,refreshAccessToken, changeCurrentPassword, updateUserAvatar, getCurrentUser, updateUserAccountDetails, updateUsercoverImg, getUserChannelProfile, getWatchHistory } from "../controllers/user.controller.js";
import { upload } from "../middlewares/multer.middlewares.js";
import { verifyJWT } from "../middlewares/auth.middlewares.js";
const router  = Router()

router.route("/register").post(
    upload.fields([
        {
            name:"avatar",
            maxCount: 1
        },
        {
            name:"coverimg",
            maxCount: 1
        }
    ]),
    registerUser
)

router.route("/login").post(loginUser)

// secured routes 

router.route("/logOut").post(verifyJWT,logOutUser)

router.route("/refersh-Token").post(refreshAccessToken)

router.route("/change_password").post(verifyJWT,changeCurrentPassword)
router.route("/current-user").get(verifyJWT,getCurrentUser)
router.route("/update-account-details").patch(verifyJWT,updateUserAccountDetails)
router.route("/update-avatar").patch(verifyJWT,upload.single("avatar"),updateUserAvatar)
router.route("update_coverImg").patch(verifyJWT,upload.single("/coverimg"),updateUsercoverImg)
router.route("/c/:username").get(verifyJWT,getUserChannelProfile)
router.route("/watchHistory").get(verifyJWT,getWatchHistory)


export default router