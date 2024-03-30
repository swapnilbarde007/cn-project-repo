const blogData = [
  {
    title: "First Blog Post",
    date: "January 1, 2022",
    content: "this is content of the first blog page.",
  },
  {
    title: "Second Blog Post",
    date: "February 1, 2022",
    content: "This is the content of the second blog post.",
  },
  {
    title: "Third Blog Post",
    date: "March 1, 2022",
    content: "This is the content of the third blog post.",
  },
];
function addBlog(blog) {
  const blogPost = document.createElement("div");
  blogPost.setAttribute("class", "blog-post");
  const blogHeader = document.createElement("div");
  blogHeader.setAttribute("class", "blog-header");
  const blogTitle = document.createElement("h2");
  blogTitle.setAttribute("class", "blog-title");
  blogTitle.innerHTML = `${blog.title}`;
  const blogDate = document.createElement("p");
  blogDate.setAttribute("class", "blog-date");

  blogDate.innerHTML = `${blog.date}`;
  const blogContent = document.createElement("p");
  blogContent.setAttribute("class", "blog-content");
  blogContent.innerHTML = `${blog.content}`;
  blogHeader.append(blogTitle, blogDate);
  blogPost.appendChild(blogHeader);
  blogPost.appendChild(blogContent);
  const blogList = document.querySelector(".blog-list");
  blogList.appendChild(blogPost);
}

//Create your function here with the name addBlog which takes a blog object as parameter
//Call each object present in blogData with addBlog.
blogData.forEach((blogData) => {
  addBlog(blogData);
});
//If page does not update the changes automatically please refresh
