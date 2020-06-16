# SemanticNetwork
This is a study project which aim to implement Semantic networks and three of its major infering techniques : 
- Mark-Propagation
- Inheritance 
- Exceptions

## Features
* [x] Ability to create a semantic network defined by a set of nodes and relations.
* [x] Mark-propagation algorithm provide the ability to infer solutions - if there's any - to the question concerning the existance of a certain relation between 2 nodes or more.
* [x] Saturate the network (infer everything that can be using Inheritance algorithm).
* [x] Deduce all the properties relating to a specific node.
* [x] Display the network's graph view

## IDE
[Netbeans](https://netbeans.org/)

## GUI editor
[SceneBuilder](https://gluonhq.com/products/scene-builder/)

## Used libraries
- [jfoenix-8.0.8](http://jfoenix.com/) (a JavaFX material design library)
- [JavaFXSmartGraph-0.9.1](https://github.com/brunomnsilva/JavaFXSmartGraph)  (a generic JavaFX graph visualization library)
- [commons-lang3-3.10](http://commons.apache.org/proper/commons-lang/download_lang.cgi)

## Procedure

### Choice
When launching the application, this interface is the first interface with which a user will interact, you must chose the algorithm you want to use before building the network.

<p align="center">
<img src="screenshots/1.png">
</p>

### Nodes entry
Define at least 2 nodes by giving a name to each node and click on `ADD`, the added nodes are displayed in the table below where you can delete any node.
  
<p align="center">
<img src="screenshots/2.png">
</p>

### Relations entry
Add at least one relation by specifying its parent/child nodes and its name, the table below as with the interface of the nodes displays all the relations added and gives the possibility of deleting those chosen.
  
<p align="center">
<img src="screenshots/3.png">
</p>

### Graph
After setting up the nodes and relations of our network and clicking on `NEXT`, an interface will appear displaying the generated graph.
  
<p align="center">
<img src="screenshots/4.png">
</p>

### Mark-Propagation
Select two nodes (M1: start, M2: goal) - you can add more nodes if you wish by switching the toggle button for each Mi - you must also specify the relation sought then click on `START` to launch the algorithm.

<p align="center">
<img src="screenshots/5.png">
</p>

### Inheritance
In this interface, the inherited relations are displayed in the text zone above, you can deduct all the properties of a certain node by selecting it from the `ChoiceBox` then by clicking on `START`, a panel of dialog will appear showing these properties.
<p align="center">
<img src="screenshots/6.png">
</p>

## Tests
Tests were carried on 3 examples taken from our course where each example deal with one of the techniques:

### Example 1 (Mark-Propagation)
<p align="center">
<img src="screenshots/5.png">
</p>

#### Arcs (id => name)

0 = > Reiter\
1 = > Modes de Représentations des connaissances\
2 = > Axe-IA\
3 = > Modes Logiques\
4 = > Modes Graphiques\
5 = > Logiques Classiques\
6 = > Logiques Non classiques\
7 = > Logique D’ordre 1\
8 = > Logique D’ordre 0\
9 = > Logique Modale\
10 = > ReiterLogique Des défauts\
11 = > Logiques De description\
12 = > Axiome A4\
13 = > Système T\
14 = > Système D\
15 = > Système S5\
16 = > Axiome A7\
17 = > a>a

#### Relations (parent, child, name)

(1, 0, dev)\
(2, 1, is_a)\
(1, 3, is_a)\
(1, 4, is_a)\
(3, 6, is_a)\
(3, 5, is_a)\
(5, 7, is_a)\
(5, 8, is_a)\
(6, 9, is_a)\
(6, 10, is_a)\
(6, 11, is_a)\
(12, 7, contient)\
(9, 13, is_a)\
(9, 14, is_a)\
(9, 15, is_a)\
(16, 13, contient)\
(16, 15, contient)\
(16, 17, is_a)


#### M1 : Modes de Représentations des connaissances
#### M2 : Axiome A7
#### Relation : contient

### Example 2 (Inheritance)
<p align="center">
<img src="screenshots/5.png">
</p>

### Example 3 (Exceptions)
<p align="center">
<img src="screenshots/5.png">
</p>

## How to contribute ?
* Enhancing graph's generation as the used library limit options as for example : making different typs arcs.
* Adding exceptions for Mark-Propagation algorithm.
* Adding other types of exceptions.
* Adding new techniques.
* Improving Mark-Propagation algorithm in a way that it traces all the M1s.
* Testing the techniques on other examples.

## Notes
- Exceptions works only with inheritance algorithm
- To add a relation of type simple exception just give it the name `is_not`
