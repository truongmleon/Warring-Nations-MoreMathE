const Generate = (props: any) => {
    const stage: string = props.stage;
    let image: string = "";
    let answers: number[] = [];
    return (  
       <div>
         <div className="flex">
         <img id="problem" width="200" src="https://miro.medium.com/v2/resize:fit:1400/1*L76A5gL6176UbMgn7q4Ybg.jpeg" alt="photo of math" />
         </div>

      <div className="flex">
      <form action="">
          <h2>Select an answer: </h2>
          {answers[0]}<input type="radio" /> <br />
          {answers[1]}<input type="radio" /> <br />
          {answers[2]}<input type="radio" /> <br />
          {answers[3]}<input type="radio" /> <br />
      </form>
  
      <button className="go">Enter</button>
      </div>
       </div>
    )
  }
  
  export default Generate;