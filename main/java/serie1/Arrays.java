package serie1;

public class Arrays {

	public static int[] getMaximumIncreasingSubsequence(int[] v, int l, int r){
		 int count=0, aux=0, begin=l, end=l; // begin and end to invalid index
		 if(l>r){ // invalid index
			 begin=0;
			 end=-1;
		 }
		 for(int i=l+1; i<=(r+1); i++){
			if(i<=r && v[i-1] < v[i]){ //incrementing a counter of one seq
				++aux;
			}
			else {
				if(count<aux){ //count=maximum
					count=aux;
					begin=l;
					end=i-1;
				}
				l=i;
				aux=0;
			}	
		}
		 return new int[]{begin,end};
	}
	
	public static int countUniquesPalindromes(String[] v, int l, int r) {
		int notRep=0;
		String aux="*"; //aux replace to know a diference 
		String rep="%";
		for(int i=l; i<=r; i++){
			if(isPalindrome(v[i])){
				if(v[i]=="") v[i]=v[i].replace("", " "); // to count with empty string
				if(!aux.contains(v[i]) && !rep.contains(v[i])){
					notRep++;
					aux+=v[i];
				}
				else{
					if(aux.contains(v[i])){
						rep+=v[i];
						aux=aux.replace(v[i], "**");
						notRep--;
					}
				}
			}
		}
		return notRep;
	}	
	
	private static boolean isPalindrome(String v) {
		v=v.toLowerCase();
		for(int i=0, j=v.length()-1; i<j;){
			if(isToIgnore(v.charAt(i))){
				i++;
			}else
			if(isToIgnore(v.charAt(j))){
				j--;
			}else
			if(v.charAt(i)!=v.charAt(j)) return false;
			else {
				i++; 
				j--;
			}
		}
		return true;
	}

	private static boolean isToIgnore(char c) {
		return c=='.' || c=='?' || c=='!' || c=='-' || c==' ' || c==',' || c==39;
	}

	public static int greatestAfterRotate(int[] v, int left, int right) {
		int val=-1;//to an empty array
		if(left>=right) return val;
		int mid = (left+right)/2; //divide 
		if(v[mid]<v[mid-1]){ 
			val = v[mid-1];//...23,25,56,15,17,... result is 56
		} else{
			if(v[mid]<v[right]) //serach the greatest number in left side
				val=greatestAfterRotate(v, left, mid);
			else
				val=greatestAfterRotate(v, mid+1, right); //right side
		}
		return val;
	}
	
	public static void changeValueInMaxHeap(int[] v, int count, int ix, int newValue){
		if(ix>count || v.length <=0 || ix>=v.length) throw new IllegalArgumentException();
		increaseKey(v, ix, newValue);
		maxHeapify(v, ix, count);
	}
	
	private static void maxHeapify(int[] a, int i, int n){//order to be max-heap
		int left = 2 * i + 1;
	    int right = 2 * i + 2;
	    int largest = i;

	    if( left < n && a[left] > a[largest] )
	        largest = left;
	    if( right < n && a[right] > a[largest] )
	        largest = right;
	    if( largest == i ) return;
	    swap(a,largest,i);
	    maxHeapify(a, largest, n);  
	}
	
	private static void increaseKey(int[] v, int i, int key ) {//modify one element 
		int parent=(i-1)/2;
		v[i] = key;
		while(i>0 && v[i]>v[parent]){
			swap(v, i, parent);
			i = parent;
			parent=(i-1)/2;
		}
	}

	private static void swap(int[] v, int sl, int i) {
		int temp = v[i];
		v[i]=v[sl];
		v[sl]=temp;
	}
	

}
