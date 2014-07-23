AndroidAnimator
===============

AndroidAnimator library aims to simplify various animations for any provided view for Android developers in a single JAR file.

Developers are able to easily animate a specific view with a single line of code using either the default parameters or customize them to their own liking.


Usage
=====

*See `sample/` folder for a working implementation of this library.*

  1. For example, if you wanted a view to mimic the bounce animation with the default parameters,
  ```
    MyAnimator.bounce(yourView);
  ```

  2. If you wanted to use your own parameters instead of the default ones,
  ```
    MyAnimator.bounce(yourView, bounceDistance, repetitions, duration, null);
  ```

  3. Here's another example of mimicking the explode animation with your own parameters as well as the addition of an animation listener.
  ```
    MyAnimator.explode(yourView, xParts, yParts, duration, new AnimationListener() {
        @Override
        public void onAnimationEnd(Animation animation) {
            *perform your own functions here when animation ends*
        }
    });
  ```


Including In Your Project
-------------------------

AndroidAnimator library is presented as a standalone JAR file.

You can include this library by downloading the JAR file into the `libs` folder located in your Android project folder.


Developed By
============

 * Umair Vatao
 * Phu Tang - <phu.tanghong@2359media.com.vn>
 * Nee Si Yao - [LinkedIn](http://sg.linkedin.com/pub/si-yao-nee/7a/a62/203/)
