import React, { useState } from "react";
import { StyleSheet, View, TouchableOpacity, Text } from "react-native";
import { TextInput, Button } from "react-native-paper";
import LottieView from "lottie-react-native";

export default function SignUpScreen({navigation}) {
  const [name, setName] = useState("");
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  return (
    <View style={styles.container}>
      <LottieView
        source={require("../assets/bganimation.json")}
        autoPlay
        loop
        style={styles.backgroundAnimation}
      />
      <View style={styles.headerContainer}>
        <Text style={styles.headerText}>Create Account</Text>
      </View>

      {/* Name Input */}
      <TextInput
        mode="outlined"
        label="Name"
        value={name}
        onChangeText={(text) => setName(text)}
        style={styles.input}
        theme={{
          colors: {
            text: "#fff", // Input text color
            placeholder: "#9e9e9e", // Placeholder color
            primary: "#6200ee", // Label color when focused
            background: "#000", // Background color inside TextInput
          },
        }}
      />

      {/* Username Input */}
      <TextInput
        mode="outlined"
        label="Username"
        value={username}
        onChangeText={(text) => setUsername(text)}
        style={styles.input}
        theme={{
          colors: {
            text: "#fff",
            placeholder: "#9e9e9e",
            primary: "#6200ee",
            background: "#000",
          },
        }}
      />

      {/* Email Input */}
      <TextInput
        mode="outlined"
        label="Email"
        value={email}
        onChangeText={(text) => setEmail(text)}
        style={styles.input}
        keyboardType="email-address"
        theme={{
          colors: {
            text: "#fff",
            placeholder: "#9e9e9e",
            primary: "#6200ee",
            background: "#000",
          },
        }}
      />

      {/* Password Input */}
      <TextInput
        mode="outlined"
        label="Password"
        value={password}
        onChangeText={(text) => setPassword(text)}
        style={styles.input}
        secureTextEntry
        theme={{
          colors: {
            text: "#fff",
            placeholder: "#9e9e9e",
            primary: "#6200ee",
            background: "#000",
          },
        }}
      />

      {/* Sign Up Button */}
      <Button
        mode="contained"
        onPress={() => console.log("Sign up pressed")}
        style={styles.button}
        labelStyle={styles.buttonText}
      >
        Sign Up
      </Button>

      {/* Sign In Link */}
      <TouchableOpacity onPress={() => navigation.navigate('SignIn') }>
        <Text style={styles.signInText}>Sign in</Text>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#000",
    padding: 20,
    justifyContent: "center",
  },
  backgroundAnimation: {
    position: "absolute",
    width: "100%",
    height: "100%",
  },
  headerContainer: {
    marginBottom: 40,
  },
  headerText: {
    fontSize: 32,
    fontWeight: "bold",
    color: "#fff",
    textAlign: "center",
  },
  input: {
    marginBottom: 15,
  },
  button: {
    marginTop: 20,
    borderRadius: 25,
  },
  buttonText: {
    fontSize: 18,
    fontWeight: "bold",
    color: "#fff",
  },
  signInText: {
    textAlign: "center",
    color: "#007bff",
    fontSize: 16,
    marginTop: 10,
  },
});
