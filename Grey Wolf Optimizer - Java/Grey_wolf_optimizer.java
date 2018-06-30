import java.io.*;
import java.util.*;

abstract class f_xj
{
abstract double func(double x[]);
}

public class Grey_wolf_optimizer
{
double r1;
double r2;
int N;
int D;
int maxiter;
double alfa[];
double beta[];
double delta[];
double Lower[];
double Upper[];
f_xj ff;
double XX[][];
double X1[][];
double X2[][];
double X3[][];
double fitness[];
double BESTVAL[];
double iterdep[];
double a[];
double A1[];
double C1[];
double A2[];
double C2[];
double A3[];
double C3[];

   public Grey_wolf_optimizer(f_xj iff,double iLower[],double iUpper[],int imaxiter,int iN)
   {
   maxiter=imaxiter;
   ff=iff;
   Lower=iLower;
   Upper=iUpper;
   N=iN;
   D=Upper.length;
   a=new double[D];
   XX=new double[N][D];
   alfa=new double[D];
   beta=new double[D];
   delta=new double[D];
   A1=new double[D];
   C1=new double[D];
   A2=new double[D];
   C2=new double[D];
   A3=new double[D];
   C3=new double[D];
   BESTVAL=new double[maxiter];
   iterdep=new double[maxiter];
    X1=new double[N][D];
   X2=new double[N][D];
    X3=new double[N][D];

   }


   double[] getminval_index(double[] a)
{
	double m=0.0;
	double b[]=new double[a.length];
	for(int i=0;i<a.length;i++)
	{b[i]=a[i];}
	double minval=a[0];
    for(int i=0;i<a.length;i++)
	{if(a[i]<minval){minval=a[i];}}
    for(int i=0;i<a.length;i++)
	{if(b[i]==minval){m=i;break;}};
	double[] dep=new double[2];
	dep[0]=minval;
	dep[1]=m;
	return dep;
}


double[] getmaxval_index(double a[])
{
double m=0.0;
double b[]=new double[a.length];
for(int i=0;i<a.length;i++)
{b[i]=a[i];}
double maxval=a[0];
for(int j=0;j<a.length;j++)
{if(a[j]>maxval){maxval=a[j];}}
for(int i=0;i<b.length;i++)
{if(b[i]==maxval){m=i;break;}}
double dep2[]=new double[2];
dep2[0]=maxval;
dep2[1]=m;
return dep2;
}

double[][] sort_and_index(double[][] XXX)
{
	   double[] yval=new double[N];
       for(int i=0;i<N;i++)
	   {yval[i]=ff.func(XXX[i]);}
	   ArrayList<Double> nfit=new ArrayList<Double>();
	   for(int i=0;i<N;i++)
	   {nfit.add(yval[i]);}
	   ArrayList<Double> nstore=new ArrayList<Double>(nfit);
	   Collections.sort(nfit);
	   double[] ret=new double[nfit.size()];
	   Iterator<Double> iterator=nfit.iterator();
	   int ii=0;
	   while(iterator.hasNext())
	   {ret[ii]=iterator.next().doubleValue();ii++;}
	   int[] indexes=new int[nfit.size()];
	   for(int n=0;n<nfit.size();n++)
	   {indexes[n]=nstore.indexOf(nfit.get(n));}
	   double[][] B=new double[N][D];
	   for(int i=0;i<N;i++)
	   {for(int j=0;j<D;j++)
	       {B[i][j]=XXX[indexes[i]][j];}}

	 return B ;
}



void init()
{
for(int i=0;i<N;i++)
  {for(int j=0;j<D;j++)
   {XX[i][j]=Lower[j]+(Upper[j]-Lower[j])*Math.random();}}


XX=sort_and_index(XX);
for(int i=0;i<D;i++)
{alfa[i]=XX[0][i];}
for(int i=0;i<D;i++)
{beta[i]=XX[1][i];}
for(int i=0;i<D;i++)
{delta[i]=XX[2][i];}

}




double[][] simplebounds(double s[][])
{
for(int i=0;i<N;i++)
 {for(int j=0;j<D;j++)
  {if(s[i][j]<Lower[j])
	 {s[i][j]=Lower[j]*((Upper[j]-Lower[j])*Math.random());}
   if(s[i][j]>Upper[j])
	 {s[i][j]=Lower[j]*((Upper[j]-Lower[j])*Math.random());}}}
return s;
}






double[][] solution()
{

init();
int iter=1;
while(iter<maxiter)
{

for(int j=0;j<D;j++)
{a[j]=2.0-((double)iter*(2.0/(double)maxiter));}

   for(int i=0;i<N;i++)
   {
       for(int j=0;j<D;j++)
       {
	      r1=Math.random();
	      r2=Math.random();
	      for(int ii=0;ii<D;ii++)
	      {A1[ii]=2.0*a[ii]*r1-a[ii];}
	      for(int ii=0;ii<D;ii++)
	      {C1[ii]=2.0*r2;}

	      X1[i][j]=alfa[j]-A1[j]*(Math.abs(C1[j]*alfa[j]-XX[i][j]));
	      X1=simplebounds(X1);
	      r1=Math.random();
	      r2=Math.random();
	      for(int ii=0;ii<D;ii++)
	      {A2[ii]=2.0*a[ii]*r1-a[ii];}
	      for(int ii=0;ii<D;ii++)
	      {C2[ii]=2.0*r2;}

	      X2[i][j]=beta[j]-A2[j]*(Math.abs(C2[j]*beta[j]-XX[i][j]));
	      X2=simplebounds(X2);
	      r1=Math.random();
	      r2=Math.random();
	      for(int ii=0;ii<D;ii++)
	      {A3[ii]=2.0*a[ii]*r1-a[ii];}
	      for(int ii=0;ii<D;ii++)
	      {C3[ii]=2.0*r2;}

	      X3[i][j]=delta[j]-A3[j]*(Math.abs(C3[j]*delta[j]-XX[i][j]));
	      X3=simplebounds(X3);
	      XX[i][j]=(X1[i][j]+X2[i][j]+X3[i][j])/3.0;

	   }
	}
	    XX=simplebounds(XX);
	    XX=sort_and_index(XX);

	    for(int i=0;i<D;i++)
	    {XX[N-1][i]=XX[0][i];}

	    for(int i=0;i<D;i++)
		{alfa[i]=XX[0][i];}
		for(int i=0;i<D;i++)
		{beta[i]=XX[1][i];}
		for(int i=0;i<D;i++)
		{delta[i]=XX[2][i];}


		BESTVAL[iter]=ff.func(XX[0]);

iter++;
}


double[][] out=new double[2][D];
		  for(int i=0;i<D;i++)
		  {out[1][i]=alfa[i];}
		  out[0][0]=ff.func(alfa);
		  return out;

}

void toStringnew()
	      {
		    double[][] in=solution();
		    System.out.println("Optimized value = "+in[0][0]);
		    for(int i=0;i<D;i++)
		    {System.out.println("x["+i+"] = "+in[1][i]);}
		  }





}
