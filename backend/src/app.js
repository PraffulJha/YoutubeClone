import express from "express"
import cors from "cors"
import cookieParser from "cookie-parser"


const app = express()

app.use(cors({
    origin: process.env.CORS_ORIGIN,
    credentials: true,
    methods: ['GET','POST'],
    allowedHeaders: ['Content-Type', 'Authorization']
}))

app.use(express.json({limit: "16kb"}))
app.use(express.urlencoded({extended:true,limit:"16kb"}))
app.use(express.static("public"))
app.use(cookieParser())
// import routes 
import userRouter from './routes/user.routes.js'
// routes decalaration
app.use("/api/v1/users/",userRouter)
export { app }