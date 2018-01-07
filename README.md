## HintableSpinner [![](https://jitpack.io/v/WindSekirun/PredicateLayout.svg)](https://jitpack.io/#WindSekirun/PredicateLayout)

[![Kotlin](https://img.shields.io/badge/kotlin-1.2.0-blue.svg)](http://kotlinlang.org)	[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)

Spinner which have HintText for Android Application, written in [Kotlin](http://kotlinalang.org)

<img src="https://github.com/WindSekirun/HintableSpinner/blob/master/sample.png" width="202" height="360">


### Usages
*rootProject/build.gradle*
```	
allprojects {
    repositories {
	    maven { url 'https://jitpack.io' }
    }
}
```

*app/build.gradle*
```
dependencies {
     implementation 'com.github.WindSekirun:HintableSpinner:1.0.0'
}
```

### Usages

#### XML
```XML
    <pyxis.uzuki.live.hintablespinner.HintableSpinner
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:hintable_hintText="Select"
        android:id="@+id/spinner"
        app:hintable_hintTextColor="@color/default_color"/>
```

#### Java
```Java
HintableSpinner spinner = findViewById(R.id.spinner);
spinner.setOnItemSelectedListener(new HintableSpinner.OnItemSelectedListener() {
        @Override
        public void onItemSelected(@NotNull View view, int position, @NotNull String item) {
            Toast.makeText(MainActivity.this, String.format("selected %s -> %s", position, item), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected() {

        }
});
spinner.addDropdownList("A", "B", "C", "D", "E");
```

### License 
```
Copyright 2017 WindSekirun (DongGil, Seo)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
