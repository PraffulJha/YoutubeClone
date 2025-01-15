import React from 'react';
import { StyleSheet, Text, View } from 'react-native';

const WelcomeScreen = ({ navigation}) => {
    
    return (
        <View>
            <Text onPress={() => navigation.navigate('SignIn')}> CLick here to login</Text>
            <Text onPress={() => navigation.navigate('SignUp')}> CLick here to SignUp</Text>
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#fff',
        alignItems: 'center',
        justifyContent: 'center',
      },
})

export default WelcomeScreen;
