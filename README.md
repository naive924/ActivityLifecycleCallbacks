# ActivityLifecycleCallbacks

## Overview
* Application 内の全ての Activity の Lifecycle 通知を、裏側でフックする
* 各 Activity に、特定のクラスを継承する事を要求しない


## Note
* Android 4.0 以上の場合は、公式に追加された Application.ActivityLifecycleCallbacks を利用し Activity の Lifecycle 通知を受ける
* 4.0 未満の場合は、onActivityCreated、 onActivityResumed だけだが、確実に通知を受ける事が出来る


## Example
* AndroidManifest.xml
```xml
    <application
        android:name="example.MyApp"
        android:icon="@drawable/icon"
        android:label="@string/app_name" >
```

* example.MyApp.java
```java
public class MyApp extends Application {
	@Override
	public void onCreate() {
		super.onCreate();

		ActivityLifecycleManager.registerLifecycle(this);

		ActivityLifecycleManager.addCallback(new ActivityLifecycleManager.Callbacks() {
			@Override
			public void onActivityResumed(Activity activity) {
				String str = String.format("onActivityResumed:%s", activity.getClass().getSimpleName());
				Log.i("MyApp", str);
			}
			@Override
			public void onActivityDestroyed(Activity activity) {
				String str = String.format("onDestroyed:%s", activity.getClass().getSimpleName());
				Log.i("MyApp", str);
			}
			

		});
	}
}
```
