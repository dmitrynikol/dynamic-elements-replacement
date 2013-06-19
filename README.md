### Dynamic elements replacement in GWT

Example: "How to extract all emails from string, replace them and change click handler for GWT elements".

Let's say you want to replace all email addresses from string to some clickable element or maybe add custom handler.
For the purpose of this example, we will assume that server side response to the client some information that contains different email addresses.
But we need to hide the addresses from the UI (so that they do not appear in plain text within HTML) and replace to anchors with text "Send an email message".
The first things we should do its write a method that replaces each email address that matches the regular expression. You can find it in [StringUtils](https://github.com/dmitrynikol/dynamic-elements-replacement/blob/master/src/com/dmitrynikol/dynamic/client/StringUtils.java) class.
Next step we will find all <a></a> elements and convert them into GWT#anchors. See main entry point class [DynamicElementsReplacement](https://github.com/dmitrynikol/dynamic-elements-replacement/blob/master/src/com/dmitrynikol/dynamic/client/DynamicElementsReplacement.java).