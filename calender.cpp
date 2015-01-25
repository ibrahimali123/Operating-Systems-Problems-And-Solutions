#include <iostream>
#include <iomanip>
using namespace std;
int main(){
cout<<"               1-@ show one month------>  press(1)"<<endl;
cout<<"               2-@ show all year months------>  press(2) "<<endl;
cout<<"               3-@ show to the current month------>  press(3)"<<endl;
cout<<"               4-@ show from the current month------>  press(4)"<<endl;
cout<<"               5-@ show specific day------>  press(5)"<<endl;
int month,year,m=0,day=1,day1,numberofdays,pressed,presss,y,NumberOfDaysInMonth;
char word,type;
cout<<"press what you need"<<endl;     cin>>pressed;
do   {
                                                                                 if(pressed==1){
cout<<"enter the month:"<<endl;      cin>>month;
cout<<"enter the year:"<<endl;       cin>>year;
int y = year-1900 ;
numberofdays = y*365.25;
if(y) numberofdays++; else numberofdays=3;
switch (month)   
 {
	  case 1:   cout<<setw(15)<<"January "<<year;   NumberOfDaysInMonth = 31;     break;
	  case 2:   cout<<setw(15)<<"February "<<year;  
      if (year % 4 == 0)                            NumberOfDaysInMonth = 29;
      else                                          NumberOfDaysInMonth = 28;     break;
	  case 3:    cout<<setw(15)<<"March "<<year;    NumberOfDaysInMonth = 31;     break;
	  case 4:    cout<<setw(15)<<"April "<<year;    NumberOfDaysInMonth = 30;     break;
	  case 5:    cout<<setw(15)<<"May "<<year;      NumberOfDaysInMonth = 31;     break;
	  case 6:    cout<<setw(15)<<"June "<<year;     NumberOfDaysInMonth = 30;     break;
	  case 7:    cout<<setw(15)<<"July "<<year;     NumberOfDaysInMonth = 31;     break;
	  case 8:    cout<<setw(15)<<"August "<<year;   NumberOfDaysInMonth = 31;     break;
	  case 9:    cout<<setw(15)<<"September"<<year; NumberOfDaysInMonth = 30;     break;
	  case 10:   cout<<setw(15)<<"October "<<year;  NumberOfDaysInMonth = 31;     break;
	  case 11:   cout<<setw(15)<<"November "<<year; NumberOfDaysInMonth = 30;     break;
	  case 12:   cout<<setw(15)<<"December "<<year; NumberOfDaysInMonth = 31;     break;
}
    if(month==2)m=31;               else if (month==3)m=59;
	else if (month==4)m=90;         else if (month==5)m=120;
	else if (month==6)m=151;        else if (month==7)m=181;
    else if (month==8)m=212;        else if (month==9)m=243;
	else if (month==10)m=273;       else if (month==11)m=304;
	else if (month==12)m=334;
    if (year%4==0&&month>2)            m++; 
	cout<<"\nSa  Su  Mo  Tu  We  Th  Fr"<<" \n"<<setw(2);
    int c =(numberofdays+m)%7;
    if(year%4!=0)                          c++;
    int z=c*4+1;
for (int i=1;i<=NumberOfDaysInMonth;i++)
{
  if(i==1)cout<<setw(z)<<i;
  if (z>25){cout<<endl;z=0;}
   z+=4;
  if (i==1)cout<<"   ";
  if (i>=2) cout<<i;
  if (i>=2) {if (i<=9){cout<<"   ";}  else  {cout<<"  ";}}
}
cout<<endl;
cout<<"            prev month-----> press (1)"<<endl;
cout<<"            next month-----> press (2)"<<endl;
cin>>presss;
while(presss ){
if(presss==1)
 {        month--; 
          if(month==0  )       {cout<<"--------in valid------only press (2)----------"<<endl; month++;} 
          if(month==1  )
switch (month)   
 {    
	  case 1:   cout<<setw(15)<<"January "<<year;   NumberOfDaysInMonth = 31;     break;
	  case 2:   cout<<setw(15)<<"February "<<year;  
      if (year % 4 == 0)                            NumberOfDaysInMonth = 29;
      else                                          NumberOfDaysInMonth = 28;     break;
	  case 3:    cout<<setw(15)<<"March "<<year;    NumberOfDaysInMonth = 31;     break;
	  case 4:    cout<<setw(15)<<"April "<<year;    NumberOfDaysInMonth = 30;     break;
	  case 5:    cout<<setw(15)<<"May "<<year;      NumberOfDaysInMonth = 31;     break;
	  case 6:    cout<<setw(15)<<"June "<<year;     NumberOfDaysInMonth = 30;     break;
	  case 7:    cout<<setw(15)<<"July "<<year;     NumberOfDaysInMonth = 31;     break;
	  case 8:    cout<<setw(15)<<"August "<<year;   NumberOfDaysInMonth = 31;     break;
	  case 9:    cout<<setw(15)<<"September"<<year; NumberOfDaysInMonth = 30;     break;
	  case 10:   cout<<setw(15)<<"October "<<year;  NumberOfDaysInMonth = 31;     break;
	  case 11:   cout<<setw(15)<<"November "<<year; NumberOfDaysInMonth = 30;     break;
	  case 12:   cout<<setw(15)<<"December "<<year; NumberOfDaysInMonth = 31;     break;
}
    if(month==1)m=31;               else if (month==2)m=59;
	else if (month==3)m=90;         else if (month==4)m=120;
	else if (month==5)m=151;        else if (month==6)m=181;
    else if (month==7)m=212;        else if (month==8)m=243;
	else if (month==9)m=273;       else if (month==10)m=304;
	else if (month==11)m=334;
    if (year%4==0&&month>2)            m++; 
	cout<<"\nSa  Su  Mo  Tu  We  Th  Fr"<<" \n"<<setw(2);
     int c =(numberofdays+m)%7;
    if(year%4!=0)                          c++;
    int z=c*4+1;
for (int i=1;i<=NumberOfDaysInMonth;i++)
{
  if(i==1)cout<<setw(z)<<i;
  if (z>25){cout<<endl;z=0;}
   z+=4;
  if (i==1)cout<<"   ";
  if (i>=2) cout<<i;
  if (i>=2) {if (i<=9){cout<<"   ";}  else  {cout<<"  ";}}
}

}
else if(presss==2)
 {     month++; 
                   if(month==13  ){cout<<"--------in valid------only press(1)------"<<endl; month--;}
switch (month)   
 {    
	  case 1:   cout<<setw(15)<<"January "<<year;   NumberOfDaysInMonth = 31;     break;
	  case 2:   cout<<setw(15)<<"February "<<year;  
      if (year % 4 == 0)                            NumberOfDaysInMonth = 29;
      else                                          NumberOfDaysInMonth = 28;     break;
	  case 3:    cout<<setw(15)<<"March "<<year;    NumberOfDaysInMonth = 31;     break;
	  case 4:    cout<<setw(15)<<"April "<<year;    NumberOfDaysInMonth = 30;     break;
	  case 5:    cout<<setw(15)<<"May "<<year;      NumberOfDaysInMonth = 31;     break;
	  case 6:    cout<<setw(15)<<"June "<<year;     NumberOfDaysInMonth = 30;     break;
	  case 7:    cout<<setw(15)<<"July "<<year;     NumberOfDaysInMonth = 31;     break;
	  case 8:    cout<<setw(15)<<"August "<<year;   NumberOfDaysInMonth = 31;     break;
	  case 9:    cout<<setw(15)<<"September"<<year; NumberOfDaysInMonth = 30;     break;
	  case 10:   cout<<setw(15)<<"October "<<year;  NumberOfDaysInMonth = 31;     break;
	  case 11:   cout<<setw(15)<<"November "<<year; NumberOfDaysInMonth = 30;     break;
	  case 12:   cout<<setw(15)<<"December "<<year; NumberOfDaysInMonth = 31;     break;
}
    if(month==2)m=31;               else if (month==3)m=59;
	else if (month==4)m=90;         else if (month==5)m=120;
	else if (month==6)m=151;        else if (month==7)m=181;
    else if (month==8)m=212;        else if (month==9)m=243;
	else if (month==10)m=273;       else if (month==11)m=304;
	else if (month==12)m=334;
    if (year%4==0&&month>2)            m++; 
	cout<<"\nSa  Su  Mo  Tu  We  Th  Fr"<<" \n"<<setw(2);
     int c =(numberofdays+m)%7;
    if(year%4!=0)                          c++;
    int z=c*4+1;
for (int i=1;i<=NumberOfDaysInMonth;i++)
{
  if(i==1)cout<<setw(z)<<i;
  if (z>25){cout<<endl;z=0;}
   z+=4;
  if (i==1)cout<<"   ";
  if (i>=2) cout<<i;
  if (i>=2) {if (i<=9){cout<<"   ";}  else  {cout<<"  ";}  }  } }
 cout<<"\n"<<endl;        cin>>presss;
 }  }
                                                                                else if(pressed==2){
 cout<<"enter the year"<<endl;
cin>>year;
int y = year-1900 ;
  numberofdays = y*365.25;
if(y) numberofdays++;   else numberofdays=3;
int NumberOfDaysInMonth;
for(int t=1;t<=12;t++)
{       
         month=t;
     switch (month)   
 {    
	  case 1:   cout<<setw(15)<<"January "<<year;   NumberOfDaysInMonth = 31;     break;
	  case 2:   cout<<setw(15)<<"February "<<year;  
      if (year % 4 == 0)                            NumberOfDaysInMonth = 29;
      else                                          NumberOfDaysInMonth = 28;     break;
	  case 3:    cout<<setw(15)<<"March "<<year;    NumberOfDaysInMonth = 31;     break;
	  case 4:    cout<<setw(15)<<"April "<<year;    NumberOfDaysInMonth = 30;     break;
	  case 5:    cout<<setw(15)<<"May "<<year;      NumberOfDaysInMonth = 31;     break;
	  case 6:    cout<<setw(15)<<"June "<<year;     NumberOfDaysInMonth = 30;     break;
	  case 7:    cout<<setw(15)<<"July "<<year;     NumberOfDaysInMonth = 31;     break;
	  case 8:    cout<<setw(15)<<"August "<<year;   NumberOfDaysInMonth = 31;     break;
	  case 9:    cout<<setw(15)<<"September"<<year; NumberOfDaysInMonth = 30;     break;
	  case 10:   cout<<setw(15)<<"October "<<year;  NumberOfDaysInMonth = 31;     break;
	  case 11:   cout<<setw(15)<<"November "<<year; NumberOfDaysInMonth = 30;     break;
	  case 12:   cout<<setw(15)<<"December "<<year; NumberOfDaysInMonth = 31;     break;
}
    if(month==2)m=31;               else if (month==3)m=59;
	else if (month==4)m=90;         else if (month==5)m=120;
	else if (month==6)m=151;        else if (month==7)m=181;
    else if (month==8)m=212;        else if (month==9)m=243;
	else if (month==10)m=273;       else if (month==11)m=304;
	else if (month==12)m=334;
    if (year%4==0&&month>2)            m++; 
	cout<<"\nSa  Su  Mo  Tu  We  Th  Fr"<<" \n"<<setw(2);
     int c =(numberofdays+m)%7;
    if(year%4!=0)                          c++;
    int z=c*4+1;
for (int i=1;i<=NumberOfDaysInMonth;i++)
{
  if(i==1)cout<<setw(z)<<i;
  if (z>25){cout<<endl;z=0;}
   z+=4;
  if (i==1)cout<<"   ";
  if (i>=2) cout<<i;
  if (i>=2) {if (i<=9){cout<<"   ";}  else  {cout<<"  ";}}   }   }
           }                                                                     else if(pressed==3) {
cout<<"enter the year"<<endl;    cin>>year;
cout<<"enter the month"<<endl;   cin>>month;
int e=month;
int y = year-1900 ;
  numberofdays = y*365.25; 
if(y) numberofdays++;  else numberofdays=3;
int NumberOfDaysInMonth;
for(int r=1;r<=e;r++)
{ month=r;
cout<<endl;
   switch (month)   
 {    
	  case 1:   cout<<setw(15)<<"January "<<year;   NumberOfDaysInMonth = 31;     break;
	  case 2:   cout<<setw(15)<<"February "<<year;  
      if (year % 4 == 0)                            NumberOfDaysInMonth = 29;
      else                                          NumberOfDaysInMonth = 28;     break;
	  case 3:    cout<<setw(15)<<"March "<<year;    NumberOfDaysInMonth = 31;     break;
	  case 4:    cout<<setw(15)<<"April "<<year;    NumberOfDaysInMonth = 30;     break;
	  case 5:    cout<<setw(15)<<"May "<<year;      NumberOfDaysInMonth = 31;     break;
	  case 6:    cout<<setw(15)<<"June "<<year;     NumberOfDaysInMonth = 30;     break;
	  case 7:    cout<<setw(15)<<"July "<<year;     NumberOfDaysInMonth = 31;     break;
	  case 8:    cout<<setw(15)<<"August "<<year;   NumberOfDaysInMonth = 31;     break;
	  case 9:    cout<<setw(15)<<"September"<<year; NumberOfDaysInMonth = 30;     break;
	  case 10:   cout<<setw(15)<<"October "<<year;  NumberOfDaysInMonth = 31;     break;
	  case 11:   cout<<setw(15)<<"November "<<year; NumberOfDaysInMonth = 30;     break;
	  case 12:   cout<<setw(15)<<"December "<<year; NumberOfDaysInMonth = 31;     break;
}
    if(month==2)m=31;               else if (month==3)m=59;
	else if (month==4)m=90;         else if (month==5)m=120;
	else if (month==6)m=151;        else if (month==7)m=181;
    else if (month==8)m=212;        else if (month==9)m=243;
	else if (month==10)m=273;       else if (month==11)m=304;
	else if (month==12)m=334;
    if (year%4==0&&month>2)            m++; 
	cout<<"\nSa  Su  Mo  Tu  We  Th  Fr"<<" \n"<<setw(2);
     int c =(numberofdays+m)%7;
    if(year%4!=0)                          c++;
    int z=c*4+1;
for (int i=1;i<=NumberOfDaysInMonth;i++)
{
  if(i==1)cout<<setw(z)<<i;
  if (z>25){cout<<endl;z=0;}
   z+=4;
  if (i==1)cout<<"   ";
  if (i>=2) cout<<i;
  if (i>=2) {if (i<=9){cout<<"   ";}  else  {cout<<"  ";}}}  }
}
                                                                                 if(pressed==4){
cout<<"enter the year"<<endl;   cin>>year;
cout<<"enter the month"<<endl;   cin>>month;
int e=month;
int y = year-1900 ;
  numberofdays= y*365.25;
if(y) numberofdays++;  else numberofdays=3;
int NumberOfDaysInMonth;
for(int r=e;r<=12;r++)
{     month=r;
       switch (month)   {    
	  case 1:   cout<<setw(15)<<"January "<<year;   NumberOfDaysInMonth = 31;     break;
	  case 2:   cout<<setw(15)<<"February "<<year;  
      if (year % 4 == 0)                            NumberOfDaysInMonth = 29;
      else                                          NumberOfDaysInMonth = 28;     break;
	  case 3:    cout<<setw(15)<<"March "<<year;    NumberOfDaysInMonth = 31;     break;
	  case 4:    cout<<setw(15)<<"April "<<year;    NumberOfDaysInMonth = 30;     break;
	  case 5:    cout<<setw(15)<<"May "<<year;      NumberOfDaysInMonth = 31;     break;
	  case 6:    cout<<setw(15)<<"June "<<year;     NumberOfDaysInMonth = 30;     break;
	  case 7:    cout<<setw(15)<<"July "<<year;     NumberOfDaysInMonth = 31;     break;
	  case 8:    cout<<setw(15)<<"August "<<year;   NumberOfDaysInMonth = 31;     break;
	  case 9:    cout<<setw(15)<<"September"<<year; NumberOfDaysInMonth = 30;     break;
	  case 10:   cout<<setw(15)<<"October "<<year;  NumberOfDaysInMonth = 31;     break;
	  case 11:   cout<<setw(15)<<"November "<<year; NumberOfDaysInMonth = 30;     break;
	  case 12:   cout<<setw(15)<<"December "<<year; NumberOfDaysInMonth = 31;     break;
}
    if(month==2)m=31;               else if (month==3)m=59;
	else if (month==4)m=90;         else if (month==5)m=120;
	else if (month==6)m=151;        else if (month==7)m=181;
    else if (month==8)m=212;        else if (month==9)m=243;
	else if (month==10)m=273;       else if (month==11)m=304;
	else if (month==12)m=334;
    if (year%4==0&&month>2)            m++; 
	cout<<"\nSa  Su  Mo  Tu  We  Th  Fr"<<" \n"<<setw(2);
     int c =(numberofdays+m)%7;
    if(year%4!=0)                          c++;
    int z=c*4+1;
for (int i=1;i<=NumberOfDaysInMonth;i++)
{
  if(i==1)cout<<setw(z)<<i;
  if (z>25){cout<<endl;z=0;}
   z+=4;
  if (i==1)cout<<"   ";
  if (i>=2) cout<<i;
  if (i>=2) {if (i<=9){cout<<"   ";}  else  {cout<<"  ";}}
}}}
                                                                                if(pressed==5){
cout<<"enter the day"<<endl;        cin>>day1;                                                                                 
cout<<"enter the year"<<endl;       cin>>year;
    if(y%4==0&&y%400==0)  numberofdays-=1;                                                                                 
  int count=numberofdays+day1; 
  if(count%7==0) cout<<"saturday"<<endl;
  else if(count%7==1) cout<<"sunday"<<endl;
  else if(count%7==2) cout<<"monday"<<endl;
  else if(count%7==3) cout<<"tuesday"<<endl;
  else if(count%7==4) cout<<"wednesday"<<endl;
  else if(count%7==5) cout<<"surthday"<<endl;
  else if(count%7==6) cout<<"friday"<<endl; 
   } cout<<"\n  do you want to continu  ? (y,n)"<<endl;   cin>>type;}
   while(type=='y'||type=='Y');
//system("pause>>null");
return 0;
}

