# Android app using Braintree and a single instance Activity

This repository holds an example of the lack of communication caused by parent activities being single instance.

Activities that are single instance live on their own Activity stack. When launching children activities, those are put on a different activity stack. This ultimately means that intent result communication does not work. 

BraintreeFragment depends heavily on intent result communication (`startActivityForResult` / `onActivityResult`) to share data between Activities. There are work around for sharing data across activities, but Braintree would continue to use intent results.
