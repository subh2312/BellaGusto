import React from "react";
import { Route, Routes } from "react-router-dom";
import CustomerRoutes from "./CustomerRoutes";

const Routers = () => {
  return (
    <Routes>
      <Route path="/*" element={<CustomerRoutes />} />
    </Routes>
  );
};

export default Routers;
