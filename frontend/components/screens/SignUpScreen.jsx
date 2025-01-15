import React, { useState } from "react";
import { StyleSheet, View, TouchableOpacity, Text, TextInput, Button } from "react-native";
import LottieView from "lottie-react-native";

export default function SignUpScreen({ navigation }) {
  const [name, setName] = useState("");
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  return (
    <View style={styles.container}>
      <LottieView
        source={require("../../assets/animations/bganimation.json")}
        autoPlay
        loop
        style={styles.backgroundAnimation}
      />
      <View style={styles.headerContainer}>
        <Text style={styles.headerText}>Create Account</Text>
      </View>

      {/* Name Input */}
      <TextInput
        placeholder="Name"
        value={name}
        onChangeText={(text) => setName(text)}
        style={styles.input}
        placeholderTextColor="#9e9e9e"
      />

      {/* Username Input */}
      <TextInput
        placeholder="Username"
        value={username}
        onChangeText={(text) => setUsername(text)}
        style={styles.input}
        placeholderTextColor="#9e9e9e"
      />

      {/* Email Input */}
      <TextInput
        placeholder="Email"
        value={email}
        onChangeText={(text) => setEmail(text)}
        style={styles.input}
        keyboardType="email-address"
        placeholderTextColor="#9e9e9e"
      />

      {/* Password Input */}
      <TextInput
        placeholder="Password"
        value={password}
        onChangeText={(text) => setPassword(text)}
        style={styles.input}
        secureTextEntry
        placeholderTextColor="#9e9e9e"
      />

      {/* Sign Up Button */}
      <Button
        title="Sign Up"
        onPress={() => console.log("Sign up pressed")}
        color="#6200ee"
      />

      {/* Sign In Link */}
      <TouchableOpacity onPress={() => navigation.navigate("SignIn")}>
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
    backgroundColor: "#1a1a1a",
    color: "#fff",
    padding: 10,
    borderRadius: 5,
  },
  signInText: {
    textAlign: "center",
    color: "#007bff",
    fontSize: 16,
    marginTop: 10,
  },
});
