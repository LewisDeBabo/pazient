
public class BinarySearchTree<ContentType extends ComparableContent<ContentType>> {

	private class BSTNode<CT extends ComparableContent<CT>> {
	  
		private CT content;
		private BinarySearchTree<CT> left, right;

		public BSTNode(CT pContent) {
			// Der Knoten hat einen linken und rechten Teilbaum, die 
			// beide von null verschieden sind. Also hat ein Blatt immer zwei 
			// leere Teilbaeume unter sich.
			this.content = pContent;
			left = new BinarySearchTree<CT>();
			right = new BinarySearchTree<CT>();
		}
		
	}

	private BSTNode<ContentType> node;

	public BinarySearchTree() {
		this.node = null;
	}

	public boolean isEmpty() {
		return this.node == null;
	}

	public void insert(ContentType pContent) {
		if (pContent != null) {
			if (isEmpty()) {
				this.node = new BSTNode<ContentType>(pContent);
			} else if (pContent.isLess(this.node.content)) {
				this.node.left.insert(pContent);
			} else if(pContent.isGreater(this.node.content)) {
				this.node.right.insert(pContent);
			}
		}
	}

	public BinarySearchTree<ContentType> getLeftTree() {
		if (this.isEmpty()) {
			return null;
		} else {
			return this.node.left;
		}
	}

	public ContentType getContent() {
		if (this.isEmpty()) {
			return null;
		} else {
			return this.node.content;
		}
	}

	public BinarySearchTree<ContentType> getRightTree() {
		if (this.isEmpty()) {
			return null;
		} else {
			return this.node.right;
		}
	}

	public void remove(ContentType pContent) {
		if (isEmpty() || pContent == null ) {
			// Abbrechen, da kein Element zum entfernen vorhanden ist.
		  return;
		}
		
		if (pContent.isLess(node.content)) {
			// Element ist im linken Teilbaum zu loeschen.
			node.left.remove(pContent);
		} else if (pContent.isGreater(node.content)) {
			// Element ist im rechten Teilbaum zu loeschen.
			node.right.remove(pContent);
		} else {
			// Element ist gefunden.
			if (node.left.isEmpty()) {
				if (node.right.isEmpty()) {
					// Es gibt keinen Nachfolger.
					node = null;
				} else {
					// Es gibt nur rechts einen Nachfolger.
					node = getNodeOfRightSuccessor();
				}
			} else if (node.right.isEmpty()) {
				// Es gibt nur links einen Nachfolger.
				node = getNodeOfLeftSuccessor();
			} else {
				// Es gibt links und rechts einen Nachfolger.
				if (getNodeOfRightSuccessor().left.isEmpty()) {
					// Der rechte Nachfolger hat keinen linken Nachfolger.
					node.content = getNodeOfRightSuccessor().content;
					node.right = getNodeOfRightSuccessor().right;
				} else {
					BinarySearchTree<ContentType> previous = node.right
							.ancestorOfSmallRight();
					BinarySearchTree<ContentType> smallest = previous.node.left;
					this.node.content = smallest.node.content;
					previous.remove(smallest.node.content);
				}
			}
		}		
	}

	public ContentType search(ContentType pContent) {
		if (this.isEmpty() || pContent == null) {
			// Abbrechen, da es kein Element zu suchen gibt.
			return null;
		} else {
			ContentType content = this.getContent();
			if (pContent.isLess(content)) {
				// Element wird im linken Teilbaum gesucht.
				return this.getLeftTree().search(pContent);
			} else if (pContent.isGreater(content)) {
				// Element wird im rechten Teilbaum gesucht.
				return this.getRightTree().search(pContent);
			} else if (pContent.isEqual(content)) {
				// Element wurde gefunden.
			  return content;				
			} else {	
			  // Dieser Fall sollte nicht auftreten.
				return null;
			}
		}
	}

	private BinarySearchTree<ContentType> ancestorOfSmallRight() {		
		if (getNodeOfLeftSuccessor().left.isEmpty()) {
			return this;
		} else {
			return node.left.ancestorOfSmallRight();
		}
	}

	private BSTNode<ContentType> getNodeOfLeftSuccessor() {
		return node.left.node;
	}

	private BSTNode<ContentType> getNodeOfRightSuccessor() {
		return node.right.node;
	}

}
