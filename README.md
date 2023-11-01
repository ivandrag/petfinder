
# PetFinder

An Android App that lists dog pets from PetFinder.com API.

## Building

1. Clone the git repository
2. Run `./gradlew build`

## Run the unit tests

1. Clone the git repository
2. Run `./gradlew test`

## Project

This project represents an app that displays a list of dogs. Each item in the list shows the dog's name and gender.
If the user selects a dog, it will open a details fragment where we will retrieve more information about the dog.

## Libraries

- [Navigation Component][navigation]
- [Retrofit][retrofit]
- [OkHttp][okhttp]
- [Koin][koin]
- [Gson][gson]
- [Glide][glide]
- [RxJava][rxjava]
- [RxAndroid][rxandroid]
- [Mockk][mockk]

[navigation]: https://developer.android.com/guide/navigation
[retrofit]: https://square.github.io/retrofit/
[okhttp]: https://square.github.io/okhttp/
[koin]: https://insert-koin.io/
[gson]: https://github.com/google/gson
[glide]: https://github.com/bumptech/glide
[rxjava]: https://www.toptal.com/android/functional-reactive-android-rxjava
[rxandroid]: https://github.com/ReactiveX/RxAndroid
[mockk]: https://mockk.io/

## Architecture
This project uses Model-View-ViewModel with Clean Architecture.

## Project Structure
**di** - dependency injection package where we created:

appModule : responsible for some Singleton classes like OkHttp, Retrofit
domainModule : responsible for domain classes : repositories, managers
dataModule : responsible for datasource
viewModelModule : responsible for view model classes

**domain** - this package is split into several other packages and is the "middle-man" between the presentation layer and the data layer.

* repository ( interface ) : middle class in our domain layer that it is responsible to pass data to the right data source. A mediator in our code.
Usually, it s the class where we should do most of our business logic.
* manager ( interface ) : class for not user actions, like refresh the access token
* model : entities that are sent to our presentation layer.

**data**
* datasource : holds classes related to remote and local work. For example, remote data sources have our api client injected and if we have a local data sources we can inject a local storage
* repository ( implementation ) : middle class in our domain layer that it is responsible to pass data between presentation and data. A mediator in our code.

**networking** - contains implementation details about our http client and interceptors.
**presentation** - contains a single activity with a navigation component. each screen is represented by a fragment, a viewModel and a layout. We interact with our views through view binding. We try to avoid having any kind of logic inside our fragments and everything is passed onto our viewModel which then decides what to do with the data.

## Testing
This project uses JUnit 4 framework and Mockk library for unit tests.

### What do we unit test?

presentation - unit test our viewModel

* Things TODO:
   * Modularization.
   * Add loading state in animal list and animal details.
   * Add pull to refresh on animal list fragment.
   * Add more unit tests to repositories, managers etc
   * Improve ui, maybe change the toolbar with a custom toolbar, add custom fonts, colors etc