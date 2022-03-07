Project to reproduce issue: https://github.com/square/anvil/issues/567

Steps to reproduce:
Run ./gradlew :app:compileKotlin
Change MyClass. Add a new method or any new statement.
Run ./gradlew :app:compileKotlin again.

The first build should work correct but after 2 or 3 build the kotlin incremental compilation should make the MyCodeGenerator throw an exception.