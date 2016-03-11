#include <iostream>
#include <algorithm>
#include <vector>
#include <string>
#include <string.h>
#include <fstream>
using namespace std;

//This function computes the min scalar, sorts the V1 in ascending and V2 is decending order.
//Then multiples V1 and V2 and adds it to sum
long min_scalar(vector<long> V1, vector<long> V2, int size_vector)
{

    long long sum = 0;
    
    sort(V1.begin(),V1.end());
    sort(V2.rbegin(), V2.rend());
    
    for(int i = 0; i < size_vector;i++){
        sum += (V1[i] * V2[i]);
    }
    return sum;
}
//This function converts the string to vector
void string_to_vect(string line, int size, vector<long> &V1)
{
    char* split;
    char* token;
    char* tofree;
    
    tofree = split = strdup(line.c_str());
    
    for(int i = 0; i < size; i++){
        token = strsep(&split, " ");
        V1.push_back(stol(token));
    }
    
    free(tofree);
}

int main (){
    
    ifstream inFile;
    ofstream outFile("A-large-practice.txt");
    
    inFile.open("/Users/Steven/Desktop/Problem A. Minimum Scalar Product/Problem A. Minimum Scalar Product/A-large-practice.txt");
    
    if(inFile.fail()){
        cout << "Error opening file" << endl;
        return -1;
    }
    
    vector<long> V1, V2;
    int test_cases, vect_size;
    long long val;
    string line;

    getline(inFile, line);
    test_cases = stoi(line);
    //Read the first string line and converts the string to a long for vec_size
    for(int i = 1; i <= test_cases; i++){
        getline(inFile, line);
        vect_size = stoi(line);
        
        //Read line for V1 and calls string to vec to convert into a vec
        getline(inFile, line);
        string_to_vect(line, vect_size, V1);
        
        //Read line for V2 and calls string to vec to convert into a vec
        getline(inFile, line);
        string_to_vect(line, vect_size, V2);
        
        val = min_scalar(V1, V2, vect_size);
        
        outFile << "Case #" << i <<": " << val << endl;
        V1.clear();
        V2.clear();
    }
    
    inFile.close();
    outFile.close();
    
}












