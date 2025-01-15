import React, { useState } from 'react';
import { StyleSheet, View, Text, TextInput, TouchableOpacity, Image, StatusBar, Animated } from 'react-native';
import LottieView from 'lottie-react-native';

const SignInScreen = ({ navigation }) => {
    const [emailFocused, setEmailFocused] = useState(false);
    const [passwordFocused, setPasswordFocused] = useState(false);
    const [emailAnimation] = useState(new Animated.Value(15));
    const [passwordAnimation] = useState(new Animated.Value(15));

    const handleFocus = (animation, isFocused) => {
        Animated.timing(animation, {
            toValue: isFocused ? -5 : 15,
            duration: 200,
            useNativeDriver: false,
        }).start();
    };

    return (
        <View style={styles.container}>
            {/* Lottie animation as background */}
            <LottieView
                source={require('../assets/bganimation.json')} // Path to your Lottie file
                autoPlay
                loop
                style={styles.backgroundAnimation}
            />
            <View style={styles.content}>
                <StatusBar barStyle="light-content" />
                <View style={styles.logoContainer}>
                   <Text style={styles.headerText}>Login </Text>
                </View>
                <View style={styles.inputContainer}>
                    <Animated.Text
                        style={[
                            styles.label,
                            {
                                top: emailAnimation,
                                fontSize: emailFocused || emailAnimation._value === -5 ? 12 : 16,
                                color: emailFocused || emailAnimation._value === -5 ? '#FF0000' : '#B0B0B0',
                            },
                        ]}
                    >
                        Email
                    </Animated.Text>
                    <TextInput
                        style={[styles.input, emailFocused && styles.inputFocused]}
                        placeholder=" "
                        placeholderTextColor="transparent"
                        keyboardType="email-address"
                        autoCapitalize="none"
                        autoCorrect={false}
                        onFocus={() => {
                            setEmailFocused(true);
                            handleFocus(emailAnimation, true);
                        }}
                        onBlur={() => {
                            setEmailFocused(false);
                            handleFocus(emailAnimation, false);
                        }}
                    />
                </View>
                <View style={styles.inputContainer}>
                    <Animated.Text
                        style={[
                            styles.label,
                            {
                                top: passwordAnimation,
                                fontSize: passwordFocused || passwordAnimation._value === -5 ? 12 : 16,
                                color: passwordFocused || passwordAnimation._value === -5 ? '#FF0000' : '#B0B0B0',
                            },
                        ]}
                    >
                        Password
                    </Animated.Text>
                    <TextInput
                        style={[styles.input, passwordFocused && styles.inputFocused]}
                        placeholder=" "
                        placeholderTextColor="transparent"
                        secureTextEntry
                        onFocus={() => {
                            setPasswordFocused(true);
                            handleFocus(passwordAnimation, true);
                        }}
                        onBlur={() => {
                            setPasswordFocused(false);
                            handleFocus(passwordAnimation, false);
                        }}
                    />
                </View>
                <TouchableOpacity style={styles.button}>
                    <Text style={styles.buttonText}>Sign In</Text>
                </TouchableOpacity>
                <TouchableOpacity>
                    <Text style={styles.forgotPassword}>Forgot Password?</Text>
                </TouchableOpacity>
                <Text style={styles.signUpText} onPress={() => navigation.navigate('SignUp')}>
                    Don't have an account? <Text style={styles.signUpLink}>Sign Up</Text>
                </Text>
            </View>
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
        position: 'relative',
        backgroundColor: '#1C1C1C',
    },
    backgroundAnimation: {
        position: 'absolute',
        width: '100%',
        height: '100%',
    },
    content: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        padding: 20,
    },
    logoContainer: {
        marginBottom: 30,
    },
    logo: {
        height: 40,
        width: 120,
    },
    inputContainer: {
        width: '100%',
        marginBottom: 15,
        position: 'relative',
    },
    label: {
        position: 'absolute',
        left: 15,
        color: '#B0B0B0',
        zIndex: 1,
    },
    input: {
        height: 50,
        width: '100%',
        backgroundColor: '#1C1C1C',
        borderRadius: 5,
        borderWidth: 1,
        borderColor: '#B0B0B0',
        marginBottom: 20,
        paddingHorizontal: 15,
        top: 10,
        color: '#FFFFFF',
    },
    inputFocused: {
        borderColor: '#FF0000',
    },
    button: {
        backgroundColor: '#FF0000',
        borderRadius: 5,
        paddingVertical: 15,
        width: '100%',
        alignItems: 'center',
    },
    buttonText: {
        color: '#FFFFFF',
        fontWeight: 'bold',
    },
    forgotPassword: {
        color: '#FF0000',
        marginTop: 10,
        textAlign: 'center',
    },
    signUpText: {
        color: '#FFFFFF',
        marginTop: 15,
        textAlign: 'center',
    },
    signUpLink: {
        color: '#FF0000',
        fontWeight: 'bold',
    },
    headerText:{
        fontSize: 32,
        fontWeight: "bold",
        color: "#fff",
        textAlign: "center",
    
    }
});

export default SignInScreen;
