import React from 'react';
import ReactDOM from 'react-dom/client';
import Geometry from '../components/Geometry';
import { useState } from 'react';

ReactDOM.createRoot(document.getElementById('stage-two') as HTMLElement).render(
  <React.StrictMode>
    <Geometry />
  </React.StrictMode>
);
