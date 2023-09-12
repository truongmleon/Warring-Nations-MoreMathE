const Generate = (props: any) => {
    const stage: string = props.stage;
    let image: string = "";
    let answers: number[] = [];

    return (  
       <div>
        <p><span className="math display">\[y = \frac&#123;a&#125;&#123;b&#125; + c^2 + d\]</span></p>
         <div className="flex">
         <img id="problem" width="200" src="https://miro.medium.com/v2/resize:fit:1400/1*L76A5gL6176UbMgn7q4Ybg.jpeg" alt="photo of math" />
         </div>

      <div className="flex">
      <form action="">
          <h2>Select an answer: </h2>
          {answers[0]}<input type="radio" name="answer" value="" /> <br />
          {answers[1]}<input type="radio" name="answer" value="" /> <br />
          {answers[2]}<input type="radio" name="answer" value="" /> <br />
          {answers[3]}<input type="radio" name="answer" value="" /> <br />
      </form>
  
      <button className="go">Enter</button>
      </div>
       </div>
    )
  }
  
  export default Generate;