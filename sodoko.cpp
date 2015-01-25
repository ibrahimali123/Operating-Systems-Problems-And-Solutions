#include <iostream>
#include <windows.h>
#include <ctime>
#include <vector>
#include<ctype.h>
#include<memory.h>
#include <conio.h>
#include <stdlib.h>
#include <stdio.h>
#include <conio.h>
#include <dos.h>
#include <iomanip>


using namespace std;
struct cor
{
    int x;
    int y;
};

int grid[9][9];
char grid2[9][9];
char grid3[9][9];
bool checkrow(int row, int column);
bool checkcolumn(int row, int column);
bool checksquare(int row, int column);
void generate ( int row , int column);
void generate2 ( int row , int column);
bool checksqure ( int i , int j);
bool checkrow2(int row, int column);
bool checkcolumn2(int row, int column);
bool checksqure2( int i , int j);
bool endofthegame();
int main()
{

HANDLE screen = GetStdHandle(STD_OUTPUT_HANDLE);
 srand (time ( NULL ) );

vector<cor>v;
int color;
char choose ;
cor temp;
char n;
char t;


        int q=rand() %9 +1;

        for(int k=0 ; k < 9 ; k++){
        int g=q;

            for(int j=0 ; j < 9 ; j++){
                bool z=0;
                while( g < 10){
                    grid[k][j]= g;
                    if(checkcolumn( k , j ) && checkrow( k , j) && checksqure ( k  ,  j)){
                    z=1;
                    break;
                }

                    else g++;
                }
                if(!z){
                    g=1;
                    j--;
                }
                else if(g==9) g=1;
             }

         }

for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                    grid2[i][j]=grid[i][j]+48;
                    }}
for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                    grid3[i][j]=grid2[i][j];
                    }}
for(int c=0;c<78;c++){
        grid3[rand() %10][rand() %10]='x';}

for(int i=0;i<9;i++){
    for(int j=0;j<9;j++){

        if(grid3[i][j]=='x'){
            temp.x=i;
            temp.y=j;
            v.push_back(temp);

        }
    }
}


color = 15;
SetConsoleTextAttribute (screen, color);
while(choose!='1'&&choose!='3')
{
system("cls");
cout<<endl<<endl<<endl<<endl<<endl<<endl<<endl;
cout<<setw(125)<<"1- Play the game . \n";
cout<<setw(124)<<"2- Instructions . \n";
cout<<setw(127)<<"3- Exit to windows . \n";
choose=getch();
system("cls");
 if(choose=='2'){
          cout<<"\n\n\n\n\n";
cout<<setw(124)<<"1-CONTROLES . \n";
cout<<setw(120)<<"2-RULES . \n";

while(true){
choose=getch();
if(choose=='1'){
          system("cls");
cout<<setw(123)<<"CONTROLES : \n\n";
cout<<"press D or -> to move to the next empty space .\n"<<"press A or <- to move to the previous empty space .\n";
cout<<"Or press from (1 -> 9) to fill thim empty spcae .\n"<<"OR press Q to leave the game and back to the main menu .\n";
cout<<"press B to submit check solution .\n\n";
cout<<"press N to back to the menu .\n";
while(choose!='n'&&choose!='N'){
choose=getch();
}
break;
}
else if(choose=='2'){
     system("cls");
cout<<setw(123)<<"RULES : \n\n"<<"- Sudoku is played over a 9x9 grid divided to 3x3 sub grids called'regions .' \n";
cout<<"- Sudoku begins with some of the grid cells already filled with numbers .\n";
cout<<"- The object of Sudoku is to fill the other empty cells with numbers "<<endl<<"between 1 and 9 (one number only in each cell).\n";
cout<<"- The same single integer may not appear twice  in any of the nine 3*3 \nsubregions of the 9*9 playing board.\n\n";
cout<<"Press N to back to the menu .\n";
while(choose!='n'&&choose!='N'){
choose=getch();
}
break;
}
}
}
}
if(choose=='3')
     return 0;
if(choose=='1'){
for(int i=0;i<v.size();){
if(n!='b'){
color = 15;
SetConsoleTextAttribute (screen, color);
cout<<" Press Q if you want to leave the game and back to the main menu .\n";
cout<<" Or press B if you want to submit your solution .\n\n";
}

        char z;
        char d;
        if( grid3[v[i].x][v[i].y]!='-')
        d = grid3[v[i].x][v[i].y];
        grid3[v[i].x][v[i].y]='-';
        generate2(9,9);
        n=getch();
        if(n=='d'||n=='D'||n==77){
        grid3[v[i].x][v[i].y]= d;
        i++;
        if(i>v.size()-1)
        i=0;
        system("cls");
        }

        else if(n=='a'||n=='A'||n==75){
            grid3[v[i].x][v[i].y]= d;
            i--;
        if(i<0)
        i=v.size()-1;
        system("cls");
        }


       else if(n=='q'||n=='Q')
            break;

       else if(n=='1'||n=='2'||n=='3'||n=='4'||n=='5'||n=='6'||n=='7'||n=='8'||n=='9'){
       grid3[v[i].x][v[i].y]=n;
       system("cls");
       if(!(checkcolumn2( v[i].x , v[i].y ) && checkrow2( v[i].x , v[i].y) && checksqure2( v[i].x  ,  v[i].y))){
       int color = 12;
       SetConsoleTextAttribute (screen, color);
       cout<<"invalid play"<<endl;
       }
       i++;
}
       else if(n=='b'||n=='B'){
          grid3[v[i].x][v[i].y]= d;
          system("cls");
          if(endofthegame()){
               cout<<"\t\t\t\t right play \n\n";
          }
          else{
               cout<<"\t\t\t\t wrong play \n";
          }
       }
       else system("cls");

       {
        if(i>v.size()-1)
        i=0;
       }
}


    system("cls");
    while(t!='1'||t!='e'||t!='E'){
           color = 15;
            SetConsoleTextAttribute (screen, color);
    cout<<"Press 1 to show the solution .\n"<<"Or press E to End the game .\n\n";
    t = getch();
    if(t == '1'){
          system("cls");
    cout<<"Or press E to End the game .\n\n";
    generate(9,9);
    while(true){
    t=getch();
    if(t=='e'||t=='E')
     break;
    }
    break;
    }
    else if(t=='e'||t=='E')
     break;
     else{};
system("cls");
}
}

system("cls");
cout<<endl<<endl<<endl<<endl<<endl<<endl<<endl;
 color=15;
     SetConsoleTextAttribute (screen, color);
cout<<setw(60)<<" Reopen the program to generate another battern . "<<endl<<endl<<endl<<endl<<endl<<endl<<endl<<endl<<endl<<endl<<endl;
    return 0;
}
    bool checkrow(int row, int column){
        for (int i = 0; i < 9; i++){
                if (i != column){
                        if (grid[row][i] == grid[row][column]){
                                return false;
                        }
                }
        }

        return true;
}
bool endofthegame(){
for(int i=0;i<9;i++){
     for(int j=0;j<9;j++){
          if(grid3[i][j]!=grid2[i][j])
               return false;
     }
}
return true;
}
bool checkcolumn(int row, int column){

        for (int i = 0; i < 9; i++){
                if (i != row){
                        if (grid[i][column] == grid[row][column]){
                                return false;
                        }
                }
        }
        return true;
}

bool checksqure ( int i , int j)
  {
                      int vsq = i/3;
                     int hsq = j/3;

                      for (int z = vsq * 3; z < (vsq*3 + 3); z++){
                                  for (int x = hsq * 3; x < (hsq*3 + 3); x++){
                                     if (!(z == i && x == j)){
                                        if (grid[ i ][ j ] == grid[z][x]){
                                                                                     return false;
                                                                                     }
                                                                 }
                                                                                     }
                                                                           }
                                                                               return true;}
    bool checkrow2(int row, int column){
        for (int i = 0; i < 9; i++){
                if (i != column){
                        if (grid3[row][i] == grid3[row][column]){
                                return false;
                        }
                }
        }

        return true;
}
bool checkcolumn2(int row, int column){

        for (int i = 0; i < 9; i++){
                if (i != row){
                        if (grid3[i][column] == grid3[row][column]){
                                return false;
                        }
                }
        }
        return true;
}

bool checksqure2( int i , int j)
  {
                      int vsq = i/3;
                     int hsq = j/3;

                      for (int z = vsq * 3; z < (vsq*3 + 3); z++){
                                  for (int x = hsq * 3; x < (hsq*3 + 3); x++){
                                     if (!(z == i && x == j)){
                                        if (grid3[ i ][ j ] == grid3[z][x]){
                                                                                     return false;
                                                                                     }
                                                                 }
                                                                                     }
                                                                           }
                                                                               return true;}


void generate ( int row , int column){
     HANDLE screen = GetStdHandle(STD_OUTPUT_HANDLE);
    cout<<endl<<endl;
    for(int i = 0 ; i < row ; i++){
          cout<<"\t\t\t";
     int color=3;
      SetConsoleTextAttribute (screen, color);
        for(int j = 0 ; j < column ; j++){
            cout<<grid[i][j]<<"   ";

        }
       cout<<endl<<endl;
    }
}

void generate2 ( int row , int column){
 	HANDLE screen = GetStdHandle(STD_OUTPUT_HANDLE);
    int color = 3;
    SetConsoleTextAttribute (screen, color);
	cout<<"************************************************************************"<<endl;
    for(int i = 0 ; i < row ; i++){
    	color=3;
    	SetConsoleTextAttribute (screen, color);
    	cout<<"*  ";
        for(int j = 0 ; j < column ; j++){
        	if(j==2||j==5)
      {
      	  	 color = 15;
        	SetConsoleTextAttribute (screen, color);
            cout<<grid3[i][j];
            color = 12;
        	SetConsoleTextAttribute (screen, color);
			cout<<"   |   ";
      }

      else
      {
      	  	 color = 15;
        	SetConsoleTextAttribute (screen, color);
            cout<<grid3[i][j];
            color = 3;
        	SetConsoleTextAttribute (screen, color);
			cout<<"   *   ";
      }


        }
      SetConsoleTextAttribute (screen, color);
      cout<< endl;
      if(i==2||i==5){
          color=12;
      SetConsoleTextAttribute (screen, color);
	  cout<<"************************************************************************"<<endl;
      }
      else{
               color=3;
      SetConsoleTextAttribute (screen, color);
	  cout<<"************************************************************************"<<endl;

      }
    }

}
