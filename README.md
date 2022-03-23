# snakat-mvp-android
Android MVP Architecture: This contains base classes/interfaces that could be extended/inherited to follow the MVP design pattern.

## Installation
1. Add this to your project as a git submodule
```sh
cd ~/sample_app/
git submodule add https://github.com/khanhbui/snakat-mvp-android.git snakat-mvp
```
2. Create a file, named *config.gradle*, which defines sdk versions, target versions and dependencies.
```groovy
ext {
    plugins = [
            library: 'com.android.library'
    ]

    android = [
            compileSdkVersion: 31,
            buildToolsVersion: "31.0.0",
            minSdkVersion    : 14,
            targetSdkVersion : 31
    ]

    dependencies = [
        appcompat: 'androidx.appcompat:appcompat:1.4.1',

        material: 'com.google.android.material:material:1.5.0',

        rxjava: 'io.reactivex.rxjava2:rxjava:2.1.8',
        rxandroid: 'io.reactivex.rxjava2:rxandroid:2.0.1'
    ]
}
```
3. Add this line on top of *build.gradle*
```groovy
apply from: "config.gradle"
```
4. Add this line to *settings.gradle*
```groovy
include ':snakat-mvp'
```
5. Add this line to dependencies section of *app/build.gradle*
```groovy
implementation project(path: ':snakat-mvp')
```

## Usage
### Contract
```java
public interface SampleContract {
  interface View extends Contract.View {
    void updateUI(int count);
  }

  interface Presenter<V extends View> extends Contract.Presenter<V> {
    void updateData();
  }
}
```
### Presenter
```java
public class SamplePresenter extends PresenterBase<SampleContract.View> implements SampleContract.Presenter<SampleContract.View> {

  private int mCount = 0;

  @Override
  public void updateData() {
    mCount++;
    mView.updateUI(mCount);
  }

}

```
### View
```java
public class SampleFragment extends FragmentBase implements SampleContract.View {

  private SampleContract.Presenter<SampleContract.View> mPresenter;

  private SampleFragmentBinding mBinding;

  @Override
  public void onResume() {
    super.onResume();

    mPresenter.updateData();
  }

  @Override
  public void updateUI(int count) {
    mBinding.countText.text = "Count: " + count;
  }

}
```

## License
```
MIT License

Copyright (c) 2022 Khanh Bui

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
