import com.gdg.build_logic.configureHiltAndroid
import com.gdg.build_logic.configureKotlinAndroid

plugins {
    id("com.android.application")
}

configureKotlinAndroid()
configureHiltAndroid()