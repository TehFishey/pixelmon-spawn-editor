package com.github.tehfishey.spawnedit.model.objects;

import static org.junit.Assert.*;

import java.nio.file.Paths;

import org.junit.Test;

public class PathTreeTest {

	@Test
	public void testTreePathAddition() {
		PathTreeNode tree = PathTreeNode.newPathTree();
		
		tree.buildChildrenFromFile(Paths.get("foo/bar.file"), "bar");
		tree.buildChildrenFromFile(Paths.get("foo/fie/fee/fum.jpeg"), "fum");
		tree.buildChildrenFromFile(Paths.get("one/fish/two/fish.exe"), "fish");
		
		assertEquals(2, tree.getChildren().size());
	}
	
	@Test
	public void testAbsoluteFilePaths() {
		PathTreeNode tree = PathTreeNode.newPathTree();
		
		tree.buildChildrenFromFile(Paths.get("foo/b/a/r.file"), "r");
		PathTreeNode fooNode = tree.getChildren().get(0);
		PathTreeNode bNode = fooNode.getChildren().get(0);
		PathTreeNode aNode = bNode.getChildren().get(0);
		PathTreeNode rNode = aNode.getChildren().get(0);
		assertEquals(Paths.get("foo/b/a/r.file"), rNode.getAbsolutePath());
	}
	
	@Test
	public void testFileMigration() {
		PathTreeNode tree = PathTreeNode.newPathTree();
	
		tree.buildChildrenFromFile(Paths.get("foo/bar.file"), "bar");
		tree.buildChildrenFromFile(Paths.get("fee/fie/foe.jpeg"), "foe");
	
		PathTreeNode fooNode = tree.getChildren().get(0);
		PathTreeNode barNode = fooNode.getChildren().get(0);
	
		PathTreeNode feeNode = tree.getChildren().get(1);
		PathTreeNode fieNode = feeNode.getChildren().get(0);
	
		barNode.migrate(fieNode);
	
		assertEquals(2, fieNode.getChildren().size());
		assertEquals(Paths.get("fee/fie/bar.file"), barNode.getAbsolutePath());
	}
	
	@Test
	public void testDirectoryMigration() {
		PathTreeNode tree = PathTreeNode.newPathTree();
		
		tree.buildChildrenFromFile(Paths.get("foo/bar.file"), "bar");
		tree.buildChildrenFromFile(Paths.get("fee/fie/foe.jpeg"), "foe");
		
		PathTreeNode fooNode = tree.getChildren().get(0);
		PathTreeNode barNode = fooNode.getChildren().get(0);
	
		PathTreeNode feeNode = tree.getChildren().get(1);
		PathTreeNode fieNode = feeNode.getChildren().get(0);
	
		fooNode.migrate(fieNode);
		
		assertEquals(2, fieNode.getChildren().size());
		assertEquals(Paths.get("fee/fie/foo"), fooNode.getAbsolutePath());
		assertEquals(Paths.get("fee/fie/foo/bar.file"), barNode.getAbsolutePath());
	}
	
}
