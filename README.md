# fermat-jooq-android

Project can be compiled using java 7

###Gradle

fermat-jooq-android:android-app **[generate]** task will generate java POJOs and DAOs classes from node.db

fermat-jooq-android:android-app **[clean]** task will delete generated classes

database node.db location: $projectDir/src/main/assets/databases/node.db

###Test
in onCreate method of the MainActivity have insert and fetchAll print node name in TextView