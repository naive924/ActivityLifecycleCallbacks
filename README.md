# ActivityLifecycleCallbacks

## Overview
* Application 内の全ての Activity の Lifecycle 通知を、裏側でフックする
** 各 Activity に、特定のクラスを継承する事を要求しない
* Android 4.0 で公式に追加された Application.ActivityLifecycleCallbacks を下位バージョンでも透過的に利用出来るようにする
** 4.0 未満の場合は、onActivityCreated、 onActivityResumed だけだが、確実に通知を受ける事が出来る



## Example

* example.MainActivity.java
```java
public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate();
			:
			:

		ActivityLifecycleManager.registerLifecycle(this);

		ActivityLifecycleManager.addCallback(new ActivityLifecycleManager.Callbacks() {
			@Override
			public void onActivityCreated(Activity activity) {
				String str = String.format("onCreated:%s", activity.getClass().getSimpleName());
				Log.i("MyApp", str);
			}
			@Override
			public void onActivityResumed(Activity activity) {
				String str = String.format("onActivityResumed:%s", activity.getClass().getSimpleName());
				Log.i("MyApp", str);
			}
		});
	}
}
```


