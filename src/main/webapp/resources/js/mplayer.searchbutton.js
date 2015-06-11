function gethref(){
    var fullsearch = document.getElementById('fullsearch').value;
    console.log(fullsearch);
    if (fullsearch.length == 0)
        fullsearch = '?';
    var pos = fullsearch.lastIndexOf('search=');
    var linknosearch;
    if(pos > -1){
        linknosearch = fullsearch.slice(0, pos);
    }
    else{
        if (fullsearch.slice(fullsearch.length - 1, fullsearch.length) == '?')
        {
            linknosearch = fullsearch;
        }
        else
        {
            linknosearch = fullsearch + '&';
        }
    }

    var searchField = document.getElementById('searchField').value;
    var searchLink = document.getElementById('searchLink');
    
    searchLink.href = searchField == "" ? linknosearch : linknosearch + ("search=" + searchField);
}
