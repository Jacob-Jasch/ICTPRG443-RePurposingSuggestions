package com.company;

public class Suggestion
{
    //Creates a string for all of the data that can be saved
    String IdeaTitle;
    String ImageFile;
    String WebLink;
    String PrimaryMaterials;
    String ConstructionProcess;
    public Suggestion(String ideaTitle, String imageFile, String webLink, String primaryMaterials, String constructionProcess)
    {
        //Set's the Strings to contain data from another class
        IdeaTitle = ideaTitle;
        ImageFile = imageFile;
        WebLink = webLink;
        PrimaryMaterials = primaryMaterials;
        ConstructionProcess = constructionProcess;
    }


    //Creates a String which will return data when called from another class
    public String getIdeaTitle()
    {
        return IdeaTitle;
    }
    //Creates a String which will set data when called from another class
    public void setIdeaTitle(String ideaTitle)
    {
        IdeaTitle = ideaTitle;
    }
    //Creates a String which will return data when called from another class
    public String getImageFile()
    {
        return ImageFile;
    }
    //Creates a String which will set data when called from another class
    public void setImageFile(String imageFile)
    {
        ImageFile = imageFile;
    }
    //Creates a String which will return data when called from another class
    public String getWebLink()
    {
        return WebLink;
    }
    //Creates a String which will set data when called from another class
    public void setWebLink(String webLink)
    {
        WebLink = webLink;
    }
    //Creates a String which will return data when called from another class
    public String getPrimaryMaterials()
    {
        return PrimaryMaterials;
    }
    //Creates a String which will set data when called from another class
    public void setPrimaryMaterials(String primaryMaterials)
    {
        PrimaryMaterials = primaryMaterials;
    }
    //Creates a String which will return data when called from another class
    public String getConstructionProcess()
    {
        return ConstructionProcess;
    }
    //Creates a String which will set data when called from another class
    public void setConstructionProcess(String constructionProcess)
    {
        ConstructionProcess = constructionProcess;
    }
}