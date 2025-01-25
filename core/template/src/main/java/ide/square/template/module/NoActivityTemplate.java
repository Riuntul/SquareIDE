package ide.square.template.module;

import android.content.Context;

import ide.square.template.BaseTemplate;
import ide.square.template.TemplateFile;

import java.io.IOException;

public class NoActivityTemplate extends BaseTemplate {
    private String projectName;
    private String packageName;

    public NoActivityTemplate(String projectName, String packageName, Context context) {
        super("No Activity", context);
        
        this.projectName = projectName;
        this.packageName = packageName;
    }

    @Override
    public void onCreate() {
        addFile(new TemplateFile("app/build.gradle.kts",
            "plugins {\n" +
            "    alias(libs.plugins.android.application)\n" +
            "}\n" +
            "\n" +
            "android {\n" +
            "    namespace = \"" + packageName + "\"\n" +
            "    compileSdk = 34\n" +
            "    \n" +
            "    defaultConfig {\n" +
            "        applicationId = \"" + packageName + "\"\n" +
            "        \n" +
            "        targetSdk = 34\n" +
            "        minSdk = 21\n" +
            "        \n" +
            "        versionCode = 1\n" +
            "        versionName = \"1.0\"\n" +
            "    }\n" +
            "    \n" +
            "    buildTypes {\n" +
            "        release {\n" +
            "            isMinifyEnabled = false\n" +
            "            proguardFiles(getDefaultProguardFile(\"proguard-android-optimize.txt\"), \"proguard-rules.pro\")\n" +
            "        }\n" +
            "    }\n" +
            "    \n" +
            "    compileOptions {\n" +
            "        sourceCompatibility = JavaVersion.VERSION_17\n" +
            "        targetCompatibility = JavaVersion.VERSION_17\n" +
            "    }\n" +
            "    \n" +
            "    buildFeatures {\n" +
            "        viewBinding = true\n" +
            "    }\n" +
            "}\n" +
            "\n" +
            "dependencies {\n" +
            "    implementation(libs.androidx.appcompat)\n" +
            "    implementation(libs.google.material)\n" +
            "}"
        ));

        addFile(new TemplateFile("settings.gradle.kts",
            "pluginManagement {\n" +
            "    repositories {\n" +
            "        google {\n" +
            "            content {\n" +
            "                includeGroupByRegex(\"com\\.android.*\")\n" +
            "                includeGroupByRegex(\"com\\.google.*\")\n" +
            "                includeGroupByRegex(\"androidx.*\")\n" +
            "            }\n" +
            "        }\n" +
            "        mavenCentral()\n" +
            "        gradlePluginPortal()\n" +
            "    }\n" +
            "}\n" +
            "\n" +
            "dependencyResolutionManagement {\n" +
            "    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)\n" +
            "    \n" +
            "    repositories {\n" +
            "        google()\n" +
            "        mavenCentral()\n" +
            "    }\n" +
            "}\n" +
            "\n" +
            "rootProject.name = \"" + projectName + "\"\n" +
            "include(\":app\")"
        ));
        
        addFile(new TemplateFile("app/src/main/res/values/strings.xml",
            "<resources>\n" +
            "    <string name=\"app_name\">" + projectName + "</string>\n" +
            "</resources>"
        ));
        
        addFile(new TemplateFile("app/src/main/res/values/themes.xml",
            "<resources xmlns:tools=\"http://schemas.android.com/tools\">\n" +
            "    <!-- Base application theme. -->\n" +
            "    <style name=\"Theme." + projectName.replaceAll("\\s", "") + "\" parent=\"Theme.MaterialComponents.DayNight.DarkActionBar\">\n" +
            "        <!-- Primary brand color. -->\n" +
            "        <item name=\"colorPrimary\">@color/purple_500</item>\n" +
            "        <item name=\"colorPrimaryVariant\">@color/purple_700</item>\n" +
            "        <item name=\"colorOnPrimary\">@color/white</item>\n" +
            "        <!-- Secondary brand color. -->\n" +
            "        <item name=\"colorSecondary\">@color/teal_200</item>\n" +
            "        <item name=\"colorSecondaryVariant\">@color/teal_700</item>\n" +
            "        <item name=\"colorOnSecondary\">@color/black</item>\n" +
            "        <!-- Status bar color. -->\n" +
            "        <item name=\"android:statusBarColor\">?attr/colorPrimaryVariant</item>\n" +
            "        <!-- Customize your theme here. -->\n" +
            "    </style>\n" +
            "</resources>"
        ));
        
        addFile(new TemplateFile("app/src/main/res/values-night/themes.xml",
            "<resources xmlns:tools=\"http://schemas.android.com/tools\">\n" +
            "    <!-- Base application theme. -->\n" +
            "    <style name=\"Theme." + projectName.replaceAll("\\s", "") + "\" parent=\"Theme.MaterialComponents.DayNight.DarkActionBar\">\n" +
            "        <!-- Primary brand color. -->\n" +
            "        <item name=\"colorPrimary\">@color/purple_200</item>\n" +
            "        <item name=\"colorPrimaryVariant\">@color/purple_700</item>\n" +
            "        <item name=\"colorOnPrimary\">@color/black</item>\n" +
            "        <!-- Secondary brand color. -->\n" +
            "        <item name=\"colorSecondary\">@color/teal_200</item>\n" +
            "        <item name=\"colorSecondaryVariant\">@color/teal_200</item>\n" +
            "        <item name=\"colorOnSecondary\">@color/black</item>\n" +
            "        <!-- Status bar color. -->\n" +
            "        <item name=\"android:statusBarColor\">?attr/colorPrimaryVariant</item>\n" +
            "        <!-- Customize your theme here. -->\n" +
            "    </style>\n" +
            "</resources>"
        ));        
    }
}