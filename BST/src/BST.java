import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class BST {

	Node Root;
	
	public void Insert(int key){
		
		Node newNode = new Node(key);
		
		if(Root == null){
			Root = newNode;
		}
		
		else{
			Node current = Root;
			Node parent;
			
			 while(true){
				 
				 parent = current;
				 
				if (key < current.key) {
					
					current = current.LeftChild;
					
					if(current == null){
						parent.LeftChild=newNode;
						newNode.Parent=parent;
						return;
					}
				}
				else{
					
					current = current.RightChild;
					
					if(current== null){
						parent.RightChild=newNode;
						newNode.Parent=parent;
						return;
					}
				}
		}
		
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BST newBST = new BST();
		
		String fileAddress = "";
		for (String s: args) {
			fileAddress+=s;
        }
		
		fileAddress = "Test.txt";
		newBST = readFileAtPath(fileAddress, newBST);
		
		
		
		OutputResult(InOrderTraverse(newBST.Root));
		
		
	}
	
	public static BST readFileAtPath(String filename, BST newBST) {
		
		
		
		if (filename == null || filename.isEmpty()) {
			System.out.println("Invalid File Path");
			return newBST;
		}
		String filePath = System.getProperty("user.dir") + "/" + filename;
		BufferedReader inputStream = null;
		
		// To handle any potential IO errors
		try {
			try {
				inputStream = new BufferedReader(new FileReader(filePath));
				String lineContent = null;
				
				
				while ((lineContent = inputStream.readLine()) != null) {
					
										
					String[] elements = lineContent.split(" ");
					
					if(elements[0].matches("B")){
						
						
						for(int i=1;i<elements.length;i++){
							newBST.Insert(Integer.parseInt(elements[i]));
						}
						
						
					}
					
				}
				
			}
			
			finally {
				if (inputStream != null)
					inputStream.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newBST;
	}
	
	
	public static String InOrderTraverse(Node root){
		
		StringBuilder SB = new StringBuilder();
		
		if(root != null){
			InOrderTraverse(root.LeftChild);
			SB.append(root.key + " ");
			InOrderTraverse(root.RightChild);
		}
		
		
		return SB.toString();
		
		
	}
	
	public static void OutputResult(String toOutPut){
		try {
			 

			File file = new File("answers.txt");
 
			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(toOutPut);
			bw.close();
 
			System.out.println("Done");
 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

class Node{
	
	Node(int k){
		key=k;
	}
	
	int key;
	Node Parent;
	Node LeftChild;
	Node RightChild;

	
}
