/*
 * Author- Bhuvan mysore
 * date -  6/5/2015
 * Program-Percolation
 */
public class Percolation {
   WeightedQuickUnionUF wqu;
   private int id[];
   public int N;
   public int count=0;
   public int xyTo1D(int x,int y)
   {  return (x*this.N)+y-(N+1);
   }
   public Percolation(int N)   // create N-by-N grid, with all sites blocked
   {   this.N=N;
	   id=new int[(N*N)];
	   wqu=new WeightedQuickUnionUF((N*N)+2);
	   for(int i=0;i<id.length;i++)
	   { id[i]=0;
    	 }
	   for(int i=1;i<=this.N;i++)
	   {   int p=xyTo1D(1,i);
	       wqu.union(p, N*N);

	   }
	   for(int j=1;j<=this.N;j++)
	   {
		   int p=xyTo1D(N,j);
		   wqu.union(p,(N*N)+1);
	   }
	   
   }
   public void open(int i, int j)          // open site (row i, column j) if it is not open already
   {  if(i==0||j==0)
	   {   throw new java.lang.IllegalArgumentException();
	   }
	   	  int p=xyTo1D(i,j);
		 // System.out.println("open"+"("+i+","+j+")");
		 //System.out.println(i+"  "+ j+"  object is  "+p );  
		 id[p]=1;
		 count++;
   }
   public boolean isOpen(int i, int j)     // is site (row i, column j) open?
   {   
	   if((i>=1 && i<=N)&&(j>=1 && j<=N))
	   {
		   int p=xyTo1D(i,j);
		   return id[p]==1;
		   
	   }
	   return false;
		   
   }
   public boolean isFull(int i, int j)     // is site (row i, column j) full?
   {  if(i==0||j==0)
	   {
		   throw new java.lang.IllegalArgumentException();
	   }
	return (isOpen(i,j)&&((isOpen(i+1,j)||isOpen(i-1,j)||isOpen(i,j+1)||isOpen(i,j-1))));
  
   }
   public boolean percolates()             // does the system percolate?
   {
	   boolean percolation=false;
	   
	   while(true)
	   {
		   int i=StdRandom.uniform(1, N+1);
		   int j=StdRandom.uniform(1, N+1);
		   if(!isOpen(i,j)) // if the site is not opened , then open it 
		   {			   
			   open(i,j);
			   
			   
			   //are there any sites for union 
			   if(isFull(i,j))
			   {
				   int p=xyTo1D(i,j);
				   //top index
				   if(i-1>=1)
				   {
					   int k=i-1;
					   int l=j;
				       if(isOpen(k,l))
				            {
				    	       
				    	     int q=xyTo1D(k,l);
				    	     this.wqu.union(p,q);
					   
				            }
				   				   
				   }
				   //right index
				   if(j+1<=N)
				   {
					   int k=i;
					   int l=j+1;
					   if(isOpen(k,l))
					   {
						   int q=xyTo1D(k,l);
						   this.wqu.union(p, q);
					   }
					   
				   }
				   //left
				   
				   if(j-1>=1)
				   {
					   int k=i;
					   int l=j-1;
					   if(isOpen(k,l))
					   {
						   int q=xyTo1D(k,l);
						   this.wqu.union(p,q);
					   }
				   }
				   
				   if(i+1<=N)
				   {
					   int k=i+1;
					   int l=j;
					   if(isOpen(k,l))
					   {
						   int q=xyTo1D(k,l);
						   this.wqu.union(p, q);
					   }
						   
				   }
			   
			   }
			   
			   //check if it percolates
		    //More efficent
			if(wqu.connected(N*N,(N*N+1)))
			{
				percolation=true;
				return percolation;
			}
			
			//Less efficient
			/*   for(int k=1;k<=N;k++)
			   {
				  int p=xyTo1D(1,k);
				  
				  for(int l=1;l<=N;l++)
				  {
					  int q=xyTo1D(N,l);
					  if(wqu.connected(p, q))
					  {
						  percolation=true;
						  return true;
					  }
					  
				  }
			   }
			   */
		  
		   }
		   
		   
	   }
	   
	   
   }
 

   public static void main(String[] args)
   { 
	   /*
	   Percolation p=new Percolation(3);
	   p.open(1, 1);
	   p.open(2,1);
	   if(p.isOpen(1, 1)&&p.isOpen(2, 1))
	   {	
		   System.out.println("it is open");
	       int p1=p.xyTo1D(1, 1);
	       int p2=p.xyTo1D(2, 1);
	       System.out.println("("+p1+","+p2+")");
	       p.wqu.union(p1, p2);
       }
	  
	   p.open(3,1);
	   if(p.isOpen(2, 1) && p.isOpen(3, 1))
	   {
		   System.out.println("it is open");
	       int p1=p.xyTo1D(2, 1);
	       int p2=p.xyTo1D(3, 1);
	       System.out.println("("+p1+","+p2+")");
	       p.wqu.union(p1, p2);
		   
	   }
	   System.out.println(p.wqu.connected(0,6));
	   */
	   Percolation p=new Percolation(20);
	   p.percolates();
	   System.out.println((double)p.count/(p.N*p.N));
	   
	  
	   
	   
   
	   
   }
}