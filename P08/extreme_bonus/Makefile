C17 = g++ --std=c++17

all:
	$(C17) -o sorted.o sorted.cpp
	@echo
	@echo './sorted.o to run'

$(TARGET):
	$(C17) -o sorted.o sorted.cpp
	@echo
	@echo './sorted.o to run'
clean:
	rm -f ./*.o
	@rm -f ./sorted
	@rm -f ./a.out
