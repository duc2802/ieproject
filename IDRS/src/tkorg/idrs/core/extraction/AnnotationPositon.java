/**
 * 
 */
package tkorg.idrs.core.extraction;

/**
 * @author DUC
 *
 */
public class AnnotationPositon {
	
	private long startAnnotationPosition;
	private long endAnnotationPosition;
	
	public AnnotationPositon() {
		
	}
	
	public boolean isEqual(AnnotationPositon other) {
		if(other.getEndAnnotationPosition() == endAnnotationPosition 
				|| other.getStartAnnotationPosition() == startAnnotationPosition) {
			return true;
		}else return false;
	}
	
	public long getStartAnnotationPosition() {
		return startAnnotationPosition;
	}

	public void setStartAnnotationPosition(long startAnnotationPosition) {
		this.startAnnotationPosition = startAnnotationPosition;
	}

	public long getEndAnnotationPosition() {
		return endAnnotationPosition;
	}

	public void setEndAnnotationPosition(long endAnnotationPosition) {
		this.endAnnotationPosition = endAnnotationPosition;
	}
	
	
}
