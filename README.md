
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://github.com/kelvinchristian/SearchableSpinner/blob/master/LICENSE)

# SearchableSpinner
Just a View that mimics Android Spinner
## Getting Started

### Install
```gradle
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}


dependencies {
	implementation 'com.github.kelvinchristian:SearchableSpinner:1.1.2'
}
```

### SearchableSpinnerView
<b>-> Features :</b>
* Simple.


Add `SearchableSpinnerView` in your XML file.

```XML
<com.kelvincodes.searchablespinnerview.SearchableSpinnerView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:id="@+id/spinner"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
```

### JAVA

```
        searchableSpinnerView = findViewById(R.id.seach);
        spinner.setContext(this).setTitle("SelectItem").setItemLayout(R.layout.row_item, R.id.text1).setData(arrayList).create();

 ```
 
