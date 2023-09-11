const Header = () => {
  return (
    <div className="">
      <h1 id="main">Warring Nations Math Maker</h1>

    <div className="flex">
    <label id="label" htmlFor="stages">Stage:</label>

    <select name="stages" id="stages">
      <option value="arithmetic">Arithmetic</option>
      <option value="geometry">Geometry</option>
      <option value="algebruh">Algebruh</option>
      <option value="calculus">Calculus</option>
      <option value="abstract">Abstract</option>
    </select>

    <button id="go">Go</button>
    </div>
    
    </div>
  )
}


export default Header;