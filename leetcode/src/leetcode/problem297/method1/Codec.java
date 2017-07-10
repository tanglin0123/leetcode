package leetcode.problem297.method1;


public class Codec {
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		if(root == null){
			return null;
		}
		
		String r = "";
		r += root.val;
		r += "," + this.serialize(root.left);
		r += "," + this.serialize(root.right);
		
		return r;
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		if(data == null || data.isEmpty()){
			return null;
		}
		
		String[] ss = data.split(",");
		return this.deserialze(ss, new int[]{0});
	}
	
	TreeNode deserialze(String[] ss, int[] index){
		int i = index[0];
		if(ss[i].equals("null")){
			++index[0];
			return null;
		}
			
		TreeNode tn = new TreeNode(Integer.parseInt(ss[i]));
		++index[0];
		tn.left = this.deserialze(ss, index);
		tn.right = this.deserialze(ss, index);
		
		return tn;
	} 
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));