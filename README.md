# Kotlin+MVVM+LiveData+Room+Koin

A reference MVVM android project with the combination of Co-routine, Room and LiveData. Before going further, let’s understand some concepts used in this project.

### Memory efficiency
For an object-oriented programming, we need to create new instances of objects, taking reference of it. But in that process it’s also necessary for us to reuse already created objects whenever it is needed. Some solutions are singleton classes (Java) or object classes (Kotlin).
Let’s talk about the same problem on project level. We don’t want to create more than one instance of an object of Activity or fragment (Android). For this purpose we use dependency injection (DI). Dependency injection plays an important role when it comes to modular code writing. In simpler word, we create instance of an object and inject it whenever it needs without creating instance again.
In android there are number of DI libraries such as Koin (Kotlin), Dagger (Java) etc. Here is the simple implementation of Koin in our project.

### Data persistence
Try not to wait for network response to show it in your app. To solve this issue we use the technique called data persistence or in a more simpler word, caching. Caching is a technique which is used to increase the speed, balances the load, increase data retrieval performance etc. Some common tools are Redis etc.
While talking about mobile, we call it data persistence. There are number of libraries in android such as Realm, Room etc. We have used Room in our project for this purpose due to its life-cycle awareness and easy to use. For image caching we can use Glide or Fresco.


### Thread safe
Let’s divide our operations into two threads i.e. main thread and background thread. All the heavy operations such as network call, media downloading, long calculations etc. should always be on background thread. And the UI rendering parts such as Data rendering, image rendering etc. should be on main thread. By following this we can utilize our CPU and prevent our app from ANR (App not responding) issue.
In kotlin library, we have co-routine to deal with it. Co-routine handles all the asynchronous or non-blocking operations on IO thread and synchronous or blocking operations on MAIN thread.

### Life cycle awareness
For an app, it is important to be life-cycle aware to prevent crashes while rotating screen or while going app in background. Suppose a case on slow internet, you sent a network call but due to slow internet it took some time to send response. During this, you exit the app and it is in background now. After sometime app got the network response and tried to render it on UI. But our cannot perform this because it is not visible currently and it got crashed.
For this purpose, there is an life-cycle aware architecture component of android, Live Data. Live Data has an observer which observe for changes and render it on UI if and only if it is okay to render.


### Overview

We have a data source on server. We fetch it and store it into the persistent database (Room). There is an observer which observes for the data on room. And render it on screen.

Few points to understand:

  - UI interacts with Room and not directly to the server.
  - Server response get stored in Room db and not in any list initially.
  - Room emits LiveData object which is observed by UI.
  - We initially show data from our persistent db and it prevents the reliability of app on the network call.




### Tech

This project consist of number of open source libraries to work properly:

* [Koin](https://github.com/InsertKoinIO/koin) - Dependency injection library
* [Retrofit](https://square.github.io/retrofit/#:~:text=Retrofit%20Configuration,but%20it%20allows%20for%20customization.) - For network call
* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - For life-cycle awareness
* [Glide](https://github.com/bumptech/glide) - For image rendering

And of course this itself is open source with a public repository
 on GitHub.

License
----
MIT License

        Copyright (c) 2020 Ashutosh Jha

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
