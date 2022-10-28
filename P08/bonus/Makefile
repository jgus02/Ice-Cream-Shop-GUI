C17 = g++ --std=c++17

all:
	$(C17) -o reverse.o reverse.cpp
	@echo
	@echo './reverse.o [args] to run'

$(TARGET):
	$(C17) -o reverse.o reverse.cpp
	@echo
	@echo './reverse.o [args] to run'
clean:
	rm -f ./*.o
	@rm ./reverse
	@rm ./a.out
