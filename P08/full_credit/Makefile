C17 = g++ --std=c++17

all:
	$(C17) -o hello_me.o  hello_me.cpp
	$(C17) -o hello_all.o hello_all.cpp

	@echo
	@echo './(FILENAME).o to run'

hello_all:
	$(C17) -o  hello_all.o hello_all.cpp
	./hello_all.o

hello_me:
	$(C17) -o  hello_me.o hello_me.cpp
	./hello_me.o

clean:
	rm -f ./*.o
