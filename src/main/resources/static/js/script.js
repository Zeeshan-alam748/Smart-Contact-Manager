
function getTheme(){
    let theme = localStorage.getItem("theme");
    return theme?'light':'dark';
}
let currentTheme = getTheme();
console.log(currentTheme);
document.addEventListener('DOMContentLoaded',()=>{
  changeTheme();
})

function changeTheme(){
  document.querySelector("html").classList.add(currentTheme)
  changePageTheme(currentTheme,currentTheme)
  const buttonTheme = document.querySelector("#themebutton")
  buttonTheme.addEventListener("click",(event)=>{
    let oldTheme = currentTheme;
    console.log('Button Clicked !')
    if(currentTheme ==='light'){
      currentTheme='dark'
    }
    else{
      currentTheme='light'
    }
    changePageTheme(oldTheme,currentTheme)
  });
}
function setTheme(theme){
  localStorage.setItem("theme",theme);
}

function changePageTheme(oldTheme,theme){
  setTheme(currentTheme)
  document.querySelector('html').classList.remove(oldTheme)
  document.querySelector('html').classList.add(theme)
    document.querySelector("#themebutton")
  .querySelector('span').textContent = theme =='light'?'Dark':'Light';
  
}