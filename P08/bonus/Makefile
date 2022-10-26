C17 = g++ --std=c++17

all:
	$(C17) -o hello_me.o  hello_me.cpp
	$(C17) -o hello_all.o hello_all.cpp

#hello_all:
#	$(C17) -o  hello_all.o hello_all.cpp
#	./hello_all.o

$(TARGET).o:
	$(C17) -o $@.o $@.cpp
	./$@.o


clean:
	rm *.o
