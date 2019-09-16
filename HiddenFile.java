package Project_Bank;

import java.io.File;

public class HiddenFile
{
	public static void main(String[] args) 
	throws Exception
	{
		String path = "This PC:\\";
		File f = new File(path);
		if(f.exists())
		{
			File[] files = f.listFiles();
			
			for (int i = 0; i < files.length; i++)
			{
				String name = files[i].getName();
				System.out.print(name);
				System.out.print(files[i].isFile()?" File":" directory");
				
				if(files[i].isHidden())
				{
					System.out.print("--------------Hidden------------");
				}
				System.out.println("");
				
			}
			
		}
		else
		{
			System.out.println("invalid path");
		}
		
	}

}
