/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cell;

/**
 *
 * @author G A S I M I A R O
 */
public interface TableActionEvent {
    public void onView(int row,Object o);
    public void onDelete(int row,Object o);
    public void onEdit(int row,Object o);
    public void onReturn(int row,Object o);
}
