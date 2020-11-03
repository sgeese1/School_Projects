# Python program to demonstrate delete operation 
# in binary search tree 

# A Binary Tree Node 
class Node: 
	# Constructor to create a new node 
	def __init__(self, key): 
		self.key = key 
		self.left = None
		self.right = None
 
def inorder(root): 
	if root is not None: 
		inorder(root.left) 
		print(root.key), 
		inorder(root.right) 

def preorder(root): 
	if root is not None: 
		print(root.key)
		preorder(root.left) 
		preorder(root.right)

def write(root, depth=0, indent=4):
    if root==None:
        return
    print("{}{}".format(" "*(indent*depth), root.key))
    if root!=None:
        write(root.left, depth=depth+1, indent=indent)
        write(root.right, depth=depth, indent=indent)

def insert(node, key): 
 
	if node is None: 
		return Node(key) 
 
	if key < node.key: 
		node.left = insert(node.left, key)
	elif key > node.key: 
		node.right = insert(node.right, key)

	else:
		return 
	
	return node 

def minValueNode(node): 
	current = node 
 
	while(current.left is not None): 
		current = current.left 

	return current 

def deleteNode(root, key): 

	if root is None: 
		return root 

	if key < root.key: 
		root.left = deleteNode(root.left, key) 
 
	elif(key > root.key): 
		root.right = deleteNode(root.right, key) 
 
	else:  
		if root.left is None : 
			temp = root.right 
			root = None
			return temp 
			
		elif root.right is None : 
			temp = root.left 
			root = None
			return temp 

		temp = minValueNode(root.right)  
		root.key = temp.key  
		root.right = deleteNode(root.right , temp.key) 

	return root 

root = None 

file = open('input.txt', 'r')

for line in file:
    for num in line.strip().split(','):
        if (num.find('+') != -1):
            root = insert(root, int(num.replace('+', '')))
        elif (num.find('-') != -1):
            root = deleteNode(root, int(num.replace('-', '')))

print("Inorder Traversal") 
inorder(root)
print("Preorder Traversal")
write(root)

