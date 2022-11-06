C17 = g++ --std=c++17

M3 = Matrix3
INT = interactive_test
TST3 = test_matrix3
READ = read_add_save

all:
	$(C17) -o $(INT).o $(M3).cpp $(INT).cpp
	$(C17) -o $(TST3).o $(M3).cpp $(TST3).cpp
	$(C17) -o $(READ).o $(M3).cpp $(READ).cpp
	
	@echo Created $(INT).o, $(TST3).o, $(READ).o

$(INT): i
$(TST3): t
$(READ): r

#lazy debugging
i:
	$(C17) -o $(INT).o $(M3).cpp $(INT).cpp
	./$(INT).o
t:
	$(C17) -o $(TST3).o $(M3).cpp $(TST3).cpp
	./$(TST3).o
r:
	$(C17) -o $(READ).o $(M3).cpp $(READ).cpp
	@echo ./$(READ).o {outputfile.m3} {inputfile1.m3} ... {inputfileN.m3}

clean:
	rm -f ./*.o
	@rm -f ./$(TST3).o
	@rm -f ./$(READ).o
	@rm -f ./$(INT).o
