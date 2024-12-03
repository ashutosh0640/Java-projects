import React,{useState, useEffect, useRef} from 'react'

const About = () => {
  const inputRef = useRef();
  const [inputValue, setInputValue] = useState("");

  useEffect(() => {
    // Automatically focus the input field when the component mounts
    inputRef.current.focus();
  }, []);
  

  function handleInput() {
    setInputValue(inputRef.current.value);
    console.log(inputRef.current.value);
  }

  return (
    <div>
      This is About page. <br/>
      <input ref={inputRef} type="text" placeholder="Type here" onChange={handleInput} />
      <input type="text" value={inputValue} />
      
    </div>
  )
  
}

export default About
