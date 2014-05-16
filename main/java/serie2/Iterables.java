package serie2;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class Iterables {
	
	public static <T> Iterable<T> flatten( final Iterable< Iterable<T> > src) {
		return new Iterable<T>(){
			@Override
			public Iterator<T> iterator() {
				return new Iterator<T>(){
					Iterator<Iterable<T>> it = src.iterator();
					Iterator<T> itin = null;
					T current =null;
					T aux=null;

					@Override
					public boolean hasNext() {
						if(current != null) return true;//exist but wasn't consumed yet
						if(itin==null){
							if(it.hasNext()){
								itin=it.next().iterator();//go insight 
								return hasNext();
							}
							else return false;
						}
						else{
							if(itin.hasNext()){
								current=itin.next();
								return true;
							}
							else {//first insight element finished
								itin=null;
								return hasNext();
							}
						}
					}

					@Override
					public T next() {
						if(hasNext()){
							aux=current;
							current=null;
							return aux;
						}
						else throw new NoSuchElementException();
					}

					@Override
					public void remove() {
						throw new UnsupportedOperationException();
						
					}
					
				};
		
				
			}
		};
	 	
	}

	public static Iterable<Integer> getSortedSubsequence(final Iterable<Integer> src){
		return new Iterable<Integer>(){
			@Override
			public Iterator<Integer> iterator() {
				return new Iterator<Integer>(){
					Iterator<Integer> it=src.iterator();
					Integer current = null;
					Integer prev = null;
					Integer aux=null;
					
					@Override
					public boolean hasNext() {
						if(current != null) return true; //exist but wasn't consumed yet
						while(it.hasNext()){//while has src elements
							prev=aux;//previous was actual from past iteration 
							current = it.next();//increment actual
							if(prev != null){//exists at least 2 element, can compare
								if(prev <= current){ //doing well
									return true;
								}
								else {
									if(it.hasNext()) current=it.next();//increment until being greater
								}
								
							}
							else return true;
						}
						return false;
					}

					@Override
					public Integer next() {
						if(hasNext()) {
							aux=current;
							current=null;
							return aux;
						}
						else
							throw new NoSuchElementException("getSortedSubsequence:Iterator - no such element");
					}
					@Override
					public void remove() {
						throw new UnsupportedOperationException("getSortedSubsequence:Iterator - remove not supported");
					}
					
				};
			}
			
		};
	}
}
