#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <map>
using namespace std;

//This sets the cords by reading in the W, L, R and uses the mod to generate the cords
void move(vector<pair<int, int>> &pos_in_vec, vector<int> &d_vec, string path, int &dir, int &i, int &j)
{
    int dx[] = { 0, 1, 0, -1 };
    int dy[] = { 1, 0, -1, 0 };
    
    for (int step_len = 0; step_len < path.length(); step_len++){
        
        if (path[step_len] == 'W'){
            
            pos_in_vec.push_back(make_pair(i, j));
            d_vec.push_back(dir);
            i += dx[dir], j += dy[dir];
            
        } else if (path[step_len] == 'L'){
            dir = (dir + 1) % 4;
            
        } else {
            dir = (dir + 3) % 4;
        }
    }
}

int main()
{
    ifstream inFile;
    ofstream outFile;
    inFile.open("/Users/Steven/Desktop/v2/v2/B-large-practice.in");
    outFile.open("B-large-practice.txt");
    
    if (!inFile) {
        cout << "error: unable to open input file: " << endl;
        return -1;
    }
    
    int num_of_cases;
    string entrance_to_exit, exit_to_entrance;
    
    inFile >> num_of_cases;
    
    for (int count = 0; count < num_of_cases; count++)
    {
        int dir = 0, pair_x = 0, pair_y = 0;
        
        vector<pair<int, int>> pos_in_vec;
        vector<int> dir_vec;
        
        outFile << "Case #" << count + 1 << ':' << endl;
        
        //Reads in until reads space for entrance and when space is found reads exit to entrance
        inFile >> entrance_to_exit >> exit_to_entrance;
        
        //Calls function to determine the cords
        move(pos_in_vec, dir_vec, entrance_to_exit, dir, pair_x, pair_y);

        int entrance_x = pair_x, entrance_y = pair_y;
        dir = (dir + 2) % 4;
        
        move(pos_in_vec, dir_vec, exit_to_entrance, dir, pair_x, pair_y);

        map <pair<int, int>, int> maze_map;
       
        //Should not be neg
        signed int ymax = 0, xmin = 0, xmax = 0;
        
        for (int i = 0; i < pos_in_vec.size(); i++){
            
            if ((pos_in_vec[i].first != 0 || pos_in_vec[i].second != 0) && (pos_in_vec[i].first != entrance_x
                || pos_in_vec[i].second != entrance_y)){

                int dir_of_per = 0;
                //The direction of the person: 0-South, 1-West, 2-North, 3-East
                switch(dir_vec[i]){
                    case 0:
                        dir_of_per = 1;
                        break;
                    case 1:
                        dir_of_per = 3;
                        break;
                    case 2:
                        dir_of_per = 0;
                        break;
                    case 3:
                        dir_of_per = 2;
                        break;
                }
              
                //Stores the paired values into the map using
                //Using the bitwise conditon shifts the binary decimal to the left depending on dir_of_per, making it 4-bit key
                maze_map[pos_in_vec[i]] |= 1 << dir_of_per;
                
                if (xmin > pos_in_vec[i].first){
                    xmin = pos_in_vec[i].first;
                }
                if (xmax < pos_in_vec[i].first){
                    xmax = pos_in_vec[i].first;
                }
                if (ymax < pos_in_vec[i].second){
                    ymax = pos_in_vec[i].second;
                }
            }
        }
        for (int j = 1; j <= ymax; j++){
            for (int i = xmin; i <= xmax; i++){
                
                char character = (maze_map[make_pair(i, j)] - 10) + 'a';
                
                if (maze_map[make_pair(i, j)] < 10){
                    outFile << maze_map[make_pair(i, j)]; // prints the numbers
                } else{
                    outFile << character;//prints letter
                }
            }
            outFile << endl;
        }
    }
    
    inFile.close();
    outFile.close();
    
}
