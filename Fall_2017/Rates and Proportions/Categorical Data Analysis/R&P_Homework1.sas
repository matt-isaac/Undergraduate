data aids;
	input Gender $ Inf_Opinion $ Health_Opinion $ Count @@;
	datalines;
Male Support Support 76 Male Support Oppose 160
Male Oppose Support 6 Male Oppose Oppose 25
Female Support Support 114 Female Support Oppose 181
Female Oppose Support 11 Female Oppose Oppose 48
;

run;

Title3 '1 A - Model with all two-way interactions, but no three-way interactions';
proc genmod data = aids;
	class Gender Inf_Opinion Health_Opinion;
	model Count = Gender|Inf_Opinion|Health_Opinion@2
		/ dist = Poisson link=log type3;
run;

Title3 '1 B - Model with all two-way interactions except Gender*Health_Opinion';
proc genmod data = aids;
	class Gender Inf_Opinion Health_Opinion;
	model Count = Gender Inf_Opinion Health_Opinion Gender*Inf_Opinion 
		      Inf_Opinion*Health_Opinion
		/ dist = Poisson link=log type3;
run;
	
Title3 '1 B - Model with all two-way interactions except Gender*Health_Opinion and Gender*Inf_Opinion';
proc genmod data = aids;
	class Gender Inf_Opinion Health_Opinion;
	model Count = Gender Inf_Opinion Health_Opinion Inf_Opinion*Health_Opinion
		/ dist = Poisson link=log type3;
run;



data personality;
	input ei $ sn $ tf $ jp $ pcount @@;
	datalines;
e s t j 77 e s f j 106 
e n t j 23 e n f j 31 
i s t j 140 i s f j 138
i n t j 13 i n f j 31
e s t p 42 e s f p 79
e n t p 18 e n f p 80
i s t p 52 i s f p 106
i n t p 35 i n f p 79
;
run;


Title3 '2 A - Model with all three-way interactions, no four way interactions';
proc genmod data = personality;
	class ei sn tf jp;
	model pcount = ei|sn|tf|jp @3
		/ dist=Poisson link=log type3;
run;

Title3 '2 B - Model with all three-way interactions, no four way interactions';
proc genmod data = personality;
	class ei sn tf jp;
	model pcount = ei|sn|tf|jp @2
		/ dist=Poisson link=log type3;
run;

Title3 '2 B - Model with all two-way interactions, except ei*jp';
proc genmod data = personality;
	class ei sn tf jp;
	model pcount = ei sn tf jp ei*sn ei*tf sn*tf sn*jp tf*jp @2
		/ dist=Poisson link=log type3;
run;

Title3 '2 B - Model with all two-way interactions, except ei*jp and ei*tf';
proc genmod data = personality;
	class ei sn tf jp;
	model pcount = ei sn tf jp ei*sn sn*tf sn*jp tf*jp @2
		/ dist=Poisson link=log type3;
run;

data spending;
input environment health cities law scount @@;
	datalines;
1 1 1 1 62 1 1 1 2 17 1 1 1 3 5
1 1 2 1 90 1 1 2 2 42 1 1 2 3 3
1 1 3 1 74 1 1 3 2 31 1 1 3 3 11
1 2 1 1 11 1 2 1 2 7 1 2 1 3 0
1 2 2 1 22 1 2 2 2 18 1 2 2 3 1 
1 2 3 1 19 1 2 3 2 14 1 2 3 3 3
1 3 1 1 2 1 3 1 2 3 1 3 1 3 1 
1 3 2 1 2 1 3 2 2 0 1 3 2 3 1 
1 3 3 1 1 1 3 3 2 3 1 3 3 3 1 
2 1 1 1 11 2 1 1 2 3 2 1 1 3 0
2 1 2 1 21 2 1 2 2 13 2 1 2 3 2
2 1 3 1 20 2 1 3 2 8 2 1 3 3 3
2 2 1 1 1 2 2 1 2 4 2 2 1 3 0
2 2 2 1 6 2 2 2 2 9 2 2 2 3 0
2 2 3 1 6 2 2 3 2 5 2 2 3 3 2
2 3 1 1 1 2 3 1 2 0 2 3 1 3 1
2 3 2 1 2 2 3 2 2 1 2 3 2 3 1
2 3 3 1 4 2 3 3 2 3 2 3 3 3 1
3 1 1 1 3 3 1 1 2 0 3 1 1 3 0
3 1 2 1 2 3 1 2 2 1 3 1 2 3 0 
3 1 3 1 9 3 1 3 2 2 3 1 3 3 1
3 2 1 1 1 3 2 1 2 0 3 2 1 3 0 
3 2 2 1 2 3 2 2 2 1 3 2 2 3 0 
3 2 3 1 4 3 2 3 2 2 3 2 3 3 0
3 3 1 1 1 3 3 1 2 0 3 3 1 3 0 
3 3 2 1 0 3 3 2 2 0 3 3 2 3 0 
3 3 3 1 1 3 3 3 2 2 3 3 3 3 3
;
run;

title3 '3 A - Log-linear model with all three-way interactions';
proc genmod data = spending;
	class environment health cities law;
	model scount = environment|health|cities|law@3
		/ dist=Poisson link=log type3;
run;

title3 '3 C - Log-linear model with all two-way interactions';
proc genmod data = spending;
	class environment health cities law;
	model scount = environment|health|cities|law@2
		/ dist=Poisson link=log type3;
run;

title3 '3 C - Log-linear model with all two-way interactions except environment*law';
proc genmod data = spending;
	class environment health cities law;
	model scount = environment health cities law 
		       environment*health environment*cities health*cities
		       health*law cities*law
		/ dist=Poisson link=log type3;
run;

title3 '3 C - Log-linear model with all two-way interactions except environment*law and health*cities';
proc genmod data = spending;
	class environment health cities law;
	model scount = environment health cities law 
		       environment*health environment*cities
		       health*law cities*law
		/ dist=Poisson link=log type3;
run;

	