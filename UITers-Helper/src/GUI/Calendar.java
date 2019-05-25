package GUI;
public class Calendar {
	String a[][] = new String[6][7];
	public Calendar()
	{
		for(int i = 0; i< 6; i++)
		{
			for(int j = 0; j< 7; j++)
			{
				this.a[i][j] = "";
			}
		}
	}
	public String [][] Xuat(int first_day_in_month, int count_day_of_month)
	{
		this.XuLy(first_day_in_month, count_day_of_month);
		for(int i = 0; i< 6; i++)
		{
			for(int j = 0; j< 7; j++)
			{
				if (this.a[i][j].compareTo("") == 0)
				{
					System.out.print(" \t");
					
				}
				else	
				{
					System.out.print(a[i][j] +"\t");
					
				}
			}
			System.out.print("\n");
		}
                return this.a;
                
	}
	public void XuLy(int first_day_in_month, int count_day_of_month)
	{
		int day = 1;
		int i = 0;
		int j = first_day_in_month -2;
		while (day <= count_day_of_month)
		{
			
			
			if (j == 7)
			{
				j = 0;
				i++;
				
			}
			a[i][j] = Integer.toString(day++);
			j++;
		}
		
	}
}