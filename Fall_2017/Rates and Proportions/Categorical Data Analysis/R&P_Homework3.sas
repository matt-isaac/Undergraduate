* Problem 1;
data ncaro;
	input district $ race $ merit $ count total @@;
	datalines;
NC black yes 24 NC black no 9 NC white yes 47 NC white no 12
NE black yes 10 NE black no 3 NE white yes 45 NE white no 8
NW black yes 5 NW black no 4 NW white yes 57 NW white no 9
SE black yes 16 SE black no 7 SE white yes 54 SE white no 10
SW black yes 7 SE black no 4 SE white yes 59 SE white no 12
;
run;

Title2 'Problem 2';
Title3 'Fit all variables, no interaction to test for mutual independence';
proc genmod data = ncaro;
	class district race merit;
	model count = district race merit
		/dist=poisson link=log type3;
run;		
		
Title3 'Fit homogeneous association model';
proc genmod data = ncaro;
	class district race merit;
	model count = district|race|merit@2
		/dist=poisson link=log type3;
run;

Title3 'Fit model with all two way interactions except District*Merit';
proc genmod data = ncaro;
	class district race merit;
	model count = district race merit district*race race*merit
		/dist=poisson link=log type3;
run;

data ncaro2;
	input district $ race $ merit nomerit @@;
	total = merit + nomerit;
	datalines;
NC black 24 9 NC white 47 12
NE black 10 3 NE white 45 8
NW black 5 4 NW white 57 9
SE black 16 7 SE white 54 10
SW black 7 4 SE white 59 12
;
run;
		
proc logistic data=ncaro2;
	class district race;
	model merit/total = district race;
run;

proc genmod data = ncaro2;
	class district race;
	model merit/total = district race
	/ d=bin type3;
	
proc genmod data = ncaro2;
	class district race;
	model merit/total = race
	/ d=binomial type3;

proc logistic data = ncaro2;
	class district race;
	model merit/total = race;

* Problem 2;
data ghq;
	input gender $ age $ score $ cases total @@;
	datalines;
male 1 low 9 531 male 1 high 12 171
male 2 low 16 500 male 2 high 16 125
male 3 low 38 644 male 3 high 31 121
male 4 low 26 275 male 4 high 16 56
male 5 low 9 90 male 5 high 10 26
female 1 low 12 568 female 1 high 33 210
female 2 low 42 596 female 2 high 47 189
female 3 low 96 765 female 3 high 71 242
female 4 low 52 327 female 4 high 45 98
female 5 low 30 179 female 5 high 21 60
;
run;

Title2 'Problem 2';
Title3 'GHQ - Fit model with all 2 way interactions';
proc genmod data = ghq;
	class gender age score;
	model cases/total = gender|age|score@2
	/d=binomial type3;
run;

Title3 'GHQ - Fit model without 2-way interactions';
proc genmod data = ghq;
	class gender age score;
	model cases/total = gender age score
	/d=binomial type3;
run;

proc logistic data = ghq;
	class gender age score;
	model cases/total = gender age score;
run;

* Problem 3;
data cancer;
	input newspaper $ lecture $ radio $ reading $ knowledge total;
	datalines;
yes yes yes yes 23 31
yes yes yes no 8 12
yes yes no yes 27 45
yes yes no no 7 13
yes no yes yes 102 169
yes no yes no 35 94
yes no no yes 201 378
yes no no no 75 231
no yes yes yes 1 4
no yes yes no 4 7
no yes no yes 3 11
no yes no no 2 12
no no yes yes 16 32
no no yes no 13 63
no no no yes 67 150
no no no no 84 477
;
run;

Title2 'Problem 3';
Title3 'Backwards Selection on Logit Model';
proc logistic data = cancer;
	class newspaper lecture radio reading;
	model knowledge/total = newspaper|lecture|radio|reading@4
	/selection=backward sls=0.05;
run;

* Problem 4
/* Generated Code (IMPORT) */
/* Source File: labetrain4.csv */
/* Source Path: /folders/myfolders/Data */
/* Code generated on: 10/18/17, 10:12 AM */

%web_drop_table(WORK.IMPORT);


FILENAME REFFILE '/folders/myfolders/Data/labetrain4.csv';

PROC IMPORT DATAFILE=REFFILE
	DBMS=CSV
	OUT=labetrain4;
	GETNAMES=YES;
RUN;

proc contents data = labetrain4; run;

/*
Title2 'Problem 4';
Title3 'Fit logistic regression model';
proc logistic data = labetrain4;
	model VETH = DistRoadTrail PercentSlope DegreeDays EvapoTransAve EvapoTransDiff MoistIndexAve
		     MoistIndexDiff PrecipAve PrecipDiff RelHumidAve RelHumidDiff PotGlobRadAve
		     PotGlobRadDiff AveTempAve AveTempDiff DayTempAve DayTempDiff MaxTempAve MaxTempDiff
		     MinTempAve MinTempDiff VapPressDefAve VapPressDefDiff SatVapPressAve SatVapPressDiff
		     AmbVapPressAve AmbVapPressDiff Elevation TransAspect 
		     / ctable rsquare;
	roc;
run;
*/

		
Title3 'Logistic regression with variable selection';
proc logistic data = labetrain4;
	model VETH = DistRoadTrail PercentSlope DegreeDays EvapoTransAve EvapoTransDiff MoistIndexAve
		     MoistIndexDiff PrecipAve PrecipDiff RelHumidAve RelHumidDiff PotGlobRadAve
		     PotGlobRadDiff AveTempAve AveTempDiff DayTempAve DayTempDiff MaxTempAve MaxTempDiff
		     MinTempAve MinTempDiff VapPressDefAve VapPressDefDiff SatVapPressAve SatVapPressDiff
		     AmbVapPressAve AmbVapPressDiff Elevation TransAspect DistRoad DistTrail
		     / ctable rsquare selection=b sls=0.05;
	roc;
run;

/*
proc hpsplit data = labetrain4;
	class Veth;
	model Veth(event = '1') = DistRoadTrail PercentSlope DegreeDays EvapoTransAve EvapoTransDiff MoistIndexAve
		     MoistIndexDiff PrecipAve PrecipDiff RelHumidAve RelHumidDiff PotGlobRadAve
		     PotGlobRadDiff AveTempAve AveTempDiff DayTempAve DayTempDiff MaxTempAve MaxTempDiff
		     MinTempAve MinTempDiff VapPressDefAve VapPressDefDiff SatVapPressAve SatVapPressDiff
		     AmbVapPressAve AmbVapPressDiff Elevation TransAspect;
	grow gini;
	prune costcomplexity;
*/
	



