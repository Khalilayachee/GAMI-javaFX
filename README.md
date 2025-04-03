# Social Media Post Management Application

This is a JavaFX application for managing social media posts and comments, with features like post creation, editing, commenting, and moderation.

## Features

### Post Management
- Create new posts with title, content, date, and image attachments
- Edit existing posts
- Delete posts
- Like/Dislike posts
- Export post details to PDF
- View posts on an interactive map (location-based)
- Search posts by title

### Comment System
- Add comments to posts
- Edit comments
- Delete comments 
- Automatic inappropriate content filtering

### User Interface
- Clean and intuitive JavaFX-based UI
- Interactive map view using GluonMaps
- Image upload support
- Responsive design

## Technical Details

### Architecture
- MVC (Model-View-Controller) pattern
- JavaFX for UI components
- MySQL database backend
- Service layer for business logic

### Key Components
- Post and Comment entities
- Database connection management
- FXML-based view templates
- Service interfaces and implementations
- Custom map markers and layers

### Dependencies
- JavaFX
- GluonMaps
- MySQL Connector
- iTextPDF for PDF export

## Getting Started

1. Ensure you have Java and JavaFX installed
2. Set up MySQL database
3. Configure database connection in `DataBase.java`
4. Run the application through `Main.java`

## Database Schema

### Posts Table
- id (Primary Key)
- title
- content
- date
- file_path
- likes
- dislikes

### Comments Table
- id (Primary Key)
- content
- date
- post_id (Foreign Key)

## Usage Example

```java
// Creating a new post
Post post = new Post(title, content, date, filePath, 0, 0);
postService.add(post);

// Adding a comment
Comment comment = new Comment(content);
commentService.add(comment);
```
